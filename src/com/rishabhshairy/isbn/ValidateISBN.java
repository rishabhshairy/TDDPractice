package com.rishabhshairy.isbn;

public class ValidateISBN {

	private static final int SHORT_ISBN_MULTIPLIER = 11;
	private static final int LONG_ISBN_MULTIPLIER = 10;
	private static final int SHORT_ISBN_LENGTH = 10;
	private static final int LONG_ISBN_LENGTH = 13;

	public boolean validateISBN(String isbn) {

		if (isbn.length() == LONG_ISBN_LENGTH) {
			return isThisValid13DigitISBN(isbn);
		} else {
			if (isbn.length() != SHORT_ISBN_LENGTH) {
				throw new NumberFormatException("ISBN must be 10 or 13 digits long");
			} else {
				return isThisValid10DigitISBN(isbn);
			}
		}

	}

	private boolean isThisValid10DigitISBN(String isbn) {
		int total = 0;

		for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {

			if (i < 9 && Character.isAlphabetic(isbn.charAt(i))) {
				throw new NumberFormatException("ISBN must contain only numbers");
			} else if (i == 9 && isbn.charAt(i) == 'X') {
				total += SHORT_ISBN_LENGTH;
			} else {
				total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGTH - i);
			}

		}
		return (total % SHORT_ISBN_MULTIPLIER == 0);
	}

	private boolean isThisValid13DigitISBN(String isbn) {
		int total = 0;
		if (isbn.length() == LONG_ISBN_LENGTH) {
			for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
				if (!Character.isDigit(isbn.charAt(i))) {
					throw new NumberFormatException("ISBN must contain only numbers");
				}
				int numericVal = Character.getNumericValue(isbn.charAt(i));
				total += (i % 2 == 0 ? numericVal * 1 : numericVal * 3);
			}
			return total % LONG_ISBN_MULTIPLIER == 0;
		}
		return false;
	}

}