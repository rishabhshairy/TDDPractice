package com.rishabhshairy.isbn;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class ValidateISBNTest {

	@Test
	void checkAValidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.validateISBN("9355201885");
		assertTrue("Value one",result);
	}
	
	@Test
	void checkAnInvalidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.validateISBN("9355201889");
		assertFalse("Checking invalid isbn case", result);
		
	}

}
