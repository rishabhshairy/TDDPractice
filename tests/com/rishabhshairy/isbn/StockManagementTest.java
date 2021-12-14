package com.rishabhshairy.isbn;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import static org.mockito.Mockito.*;

public class StockManagementTest {

	@Test
	public void testCanGetLocatorCode() {

		/**
		 * Creating Stubs for data service
		 * 
		 */

		ExternalISBNDataService webService = new ExternalISBNDataService() {

			@Override
			public Book lookup(String isbn) {
				// TODO Auto-generated method stub
				return new Book(isbn, "Of mice and men", "J. Steinbeck");
			}
		};

		ExternalISBNDataService databaseService = new ExternalISBNDataService() {

			@Override
			public Book lookup(String isbn) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		StockManager manager = new StockManager();
		manager.setDataBaseService(databaseService);
		manager.setWebService(webService);
		String isbn = "0140177396";

		String locatorCode = manager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

	@Test
	public void databaseIsUsedIfDataIsPresent() {
		ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
		ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

		when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

		StockManager manager = new StockManager();
		manager.setDataBaseService(databaseService);
		manager.setWebService(webService);
		String isbn = "0140177396";
		String locatorCode = manager.getLocatorCode(isbn);

		verify(databaseService, times(1)).lookup(isbn);
		verify(webService, times(0)).lookup(isbn);
	}

	@Test
	public void webServiceIsUsedIfDataIsNotInDatabase() {
		ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
		ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

		when(databaseService.lookup("0140177396")).thenReturn(null);
		when(webService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

		StockManager manager = new StockManager();
		manager.setDataBaseService(databaseService);
		manager.setWebService(webService);
		String isbn = "0140177396";
		String locatorCode = manager.getLocatorCode(isbn);

		verify(databaseService, times(1)).lookup(isbn);
		verify(webService, times(1)).lookup(isbn);
	}
}
