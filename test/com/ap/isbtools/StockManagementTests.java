package com.ap.isbtools;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

public class StockManagementTests {

    ExternalIsbnDataService testWebService;
    ExternalIsbnDataService testDatabaseService;
    StockManager stockManager;

    @Before
    public void setup() {
        testWebService = mock(ExternalIsbnDataService.class);
        testDatabaseService = mock(ExternalIsbnDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
    }

    @Test
    public void testCanGetACorrectLocatorCode() {
        when(testWebService.lookup(anyString())).thenReturn(new Book("1472143930", "The Pottery Cottage Murders", "Carol Ann Lee"));
        when(testDatabaseService.lookup(anyString())).thenReturn(null);

        String isbn = "1472143930";
        String locatorCode = stockManager.getLocatorCode(isbn);
        
        assertEquals("3930C4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        when(testDatabaseService.lookup("1472143930")).thenReturn(new Book("1472143930", "abc", "abc"));

        String isbn = "1472143930";
        stockManager.getLocatorCode(isbn);

        verify(testDatabaseService, times(1)).lookup("1472143930");
        verify(testWebService, never()).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresent() {
        when(testDatabaseService.lookup("1472143930")).thenReturn(null);
        when(testWebService.lookup("1472143930")).thenReturn(new Book("1472143930", "abc", "abc"));

        String isbn = "1472143930";
        stockManager.getLocatorCode(isbn);

        verify(testDatabaseService).lookup("1472143930");
        verify(testWebService).lookup("1472143930");
    }
}
