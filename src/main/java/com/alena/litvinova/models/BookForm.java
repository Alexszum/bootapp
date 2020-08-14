package com.alena.litvinova.models;

import javax.persistence.Column;

public class BookForm {
	
	private String bookName;
	 
	private String bookDescription;
	 
	private Integer bookAuthorId;

	private Integer price;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getBookAuthorId() {
		return bookAuthorId;
	}

	public void setBookAuthorId(Integer bookAuthorId) {
		this.bookAuthorId = bookAuthorId;
	}
}
