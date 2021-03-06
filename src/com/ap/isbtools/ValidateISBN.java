package com.ap.isbtools;

public class ValidateISBN {

    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkISBN(String isbn) {
        if (isbn.length() == LONG_ISBN_LENGTH) {
            return checkLongDigitISBN(isbn);
        } else if (isbn.length() == SHORT_ISBN_LENGTH){
            return checkShortDigitISBN(isbn);
        }
        throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long");
    }

    public boolean checkShortDigitISBN(String isbn) {
        int total = 0;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i == 9 && isbn.charAt(i) == 'X') {
                    total +=SHORT_ISBN_LENGTH;
                } else {
                    throw new NumberFormatException("ISBN must be numeric");
                }
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGTH - i);
            }
        }
        return (total % SHORT_ISBN_MULTIPLIER == 0);
    }

    public boolean checkLongDigitISBN(String isbn) {
        int total = 0;
        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                throw new NumberFormatException("ISBN must be numeric");
            }
            if (i % 2 == 0) {
                total += Character.getNumericValue(isbn.charAt(i));
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * 3;
            }
        }
        return  (total % LONG_ISBN_MULTIPLIER == 0);
    }
}
