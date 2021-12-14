package com.rishabhshairy.isbn;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidateISBNTest {

	@Test
	void checkAValidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.validateISBN(9355201);
		assertTrue(result);
	}
	
	@Test
	void checkAnInvalidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.validateISBN(2355201);
		assertFalse(result);
	}

}
