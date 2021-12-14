package com.rishabhshairy.isbn;

public class StockManager {

	private ExternalISBNDataService webService;
	private ExternalISBNDataService databaseService;

	public void setWebService(ExternalISBNDataService webService) {
		this.webService = webService;
	}

	public void setDataBaseService(ExternalISBNDataService dataBaseService) {
		this.databaseService = dataBaseService;
	}

	public String getLocatorCode(String isbn) {
		// TODO Auto-generated method stub
		Book book = databaseService.lookup(isbn);
		if (book == null) {
			book = webService.lookup(isbn);
		}
		StringBuilder locator = new StringBuilder();
		locator.append(isbn.substring(isbn.length() - 4));
		locator.append(book.getAuthor().substring(0, 1));
		locator.append(book.getTitle().split(" ").length);
		return locator.toString();
	}

}
