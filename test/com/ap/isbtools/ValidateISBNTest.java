package com.ap.isbtools;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateISBNTest {

    @Test
    public void checkAValid10DigitISBN() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("1529124867");
        assertTrue("first value", result);
        result = validateISBN.checkISBN("1472143930");
        assertTrue("second value", result);
    }

    @Test
    public void checkAValid13DigitISBN() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("9780134494166");
        assertTrue("first 13 digit ISBN", result);
        result = validateISBN.checkISBN("9781097268535");
        assertTrue("second 13 digit", result);
    }

    @Test
    public void tenDigitISBNNumbersEndingInAnXAreValid() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("032157351X");
        assertTrue(result);
    }

    @Test
    public void check10DigitInvalidISBN() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("1517671273");
        assertFalse(result);
    }

    @Test
    public void check13DigitInvalidISBN() {
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.checkISBN("9781097268537");
        assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    public void nineDigitISBNAreNotAllowed() {
        ValidateISBN validateISBN = new ValidateISBN();
        validateISBN.checkISBN("151767127");
    }

    @Test(expected = NumberFormatException.class)
    public void onlyISBNDigitsAllowed() {
        ValidateISBN validateISBN = new ValidateISBN();
        validateISBN.checkISBN("hellowworld");
    }

}
