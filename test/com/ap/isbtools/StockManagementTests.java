package com.ap.isbtools;

import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

public class StockManagementTests {

    @Test
    public void testCanGetACorrectLocatorCode() {
        ExternalIsbnDataService testWebService = new ExternalIsbnDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "The Pottery Cottage Murders", "Carol Ann Lee");
            }
        };

        ExternalIsbnDataService testDatabaseService = new ExternalIsbnDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };
        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);

        String isbn = "1472143930";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("3930C4", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        ExternalIsbnDataService databaseService = mock(ExternalIsbnDataService.class);
        ExternalIsbnDataService webService = mock(ExternalIsbnDataService.class);

        when(databaseService.lookup("1472143930")).thenReturn(new Book("1472143930", "abc", "abc"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "1472143930";
        stockManager.getLocatorCode(isbn);

        verify(databaseService, times(1)).lookup("1472143930");
        verify(webService, never()).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresent() {
        ExternalIsbnDataService databaseService = mock(ExternalIsbnDataService.class);
        ExternalIsbnDataService webService = mock(ExternalIsbnDataService.class);

        when(databaseService.lookup("1472143930")).thenReturn(null);
        when(webService.lookup("1472143930")).thenReturn(new Book("1472143930", "abc", "abc"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "1472143930";
        stockManager.getLocatorCode(isbn);

        verify(databaseService).lookup("1472143930");
        verify(webService).lookup("1472143930");
    }
}
