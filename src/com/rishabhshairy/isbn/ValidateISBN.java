package com.rishabhshairy.isbn;

public class ValidateISBN {

	public boolean validateISBN(String isbn) {
		// TODO Auto-generated method stub
		int total = 0;
		for (int i = 0; i < 10; i++) {
			total += isbn.charAt(i) * (10 - i);
		}
		if (total % 11 == 0) {
			return true;
		}
		return false;
	}

}
