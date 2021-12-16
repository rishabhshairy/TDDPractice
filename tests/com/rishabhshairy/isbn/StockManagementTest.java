package com.rishabhshairy.isbn;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class StockManagementTest {

	StockManager manager;
	ExternalISBNDataService testWebService;
	ExternalISBNDataService testDatabaseService;

	@Before
	public void setup() {
		System.out.println("setup running");
		testWebService = mock(ExternalISBNDataService.class);
		testDatabaseService = mock(ExternalISBNDataService.class);
		manager = new StockManager();
		manager.setDataBaseService(testDatabaseService);
		manager.setWebService(testWebService);
	}

	@Test
	public void testCanGetLocatorCode() {

		when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Of mice and men", "J. Steinbeck"));

		when(testDatabaseService.lookup(anyString())).thenReturn(null);

		String isbn = "0140177396";
		String locatorCode = manager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

	@Test
	public void databaseIsUsedIfDataIsPresent() {

		when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

		String isbn = "0140177396";
		String locatorCode = manager.getLocatorCode(isbn);

		verify(testDatabaseService, times(1)).lookup(isbn);
		verify(testWebService, times(0)).lookup(isbn);
	}

	@Test
	public void testWebServiceIsUsedIfDataIsNotInDatabase() {

		when(testDatabaseService.lookup("0140177396")).thenReturn(null);
		when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

		String isbn = "0140177396";
		String locatorCode = manager.getLocatorCode(isbn);

		verify(testDatabaseService, times(1)).lookup(isbn);
		verify(testWebService, times(1)).lookup(isbn);
	}
}
