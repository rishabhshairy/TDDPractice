package com.rishabhshairy.isbn;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidateISBNTest {

	@Test
	public void checkAValid10DigitsISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.validateISBN("9355201885");
		assertTrue("Value one", result);
	}

	@Test
	public void tenDigitsISBNNumbersEndingInAnXAreValid() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.validateISBN("012000030X");
		assertTrue("Value one", result);
	}

	@Test
	public void checkAValid13DigitsISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.validateISBN("9788175992771");
		assertTrue("Value one", result);
	}

	@Test
	public void checkAnInvalid13Digits() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.validateISBN("9788175992770");
		assertFalse("Value one", result);
	}
	
	@Test
	public void checkAnInvalid10DigitsISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.validateISBN("9355201889");
		assertFalse("Checking invalid isbn case", result);

	}

	@Test(expected = NumberFormatException.class)
	public void nineDigitsNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.validateISBN("935520188");
		validator.validateISBN("helloworld");

	}

	@Test(expected = NumberFormatException.class)
	public void nonNumbericISBNsNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.validateISBN("helloworld");
	}

}
