package com.alena.litvinova.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOOKS")
public class Book {

	public Book(){}
	public Book(Integer id, String name, String description, Integer authorId, Integer price){
		this.id = id;
		this.bookName = name;
		this.bookDescription = description;
		this.bookAuthorId = authorId;
		this.price = price;
	}
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "ID")
	private Integer id;
	 
	 @Column(name = "BOOKNAME")
	private String bookName;
	 
	 @Column(name = "BOOKDESCRIPTION")
	private String bookDescription;
	 
	 @Column(name = "BOOKAUTHORID")
	private Integer bookAuthorId;

	 @Column(name = "BOOKPRICE")
	 private Integer price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getBookAuthorId() {
		return bookAuthorId;
	}

	public void setBookAuthorId(Integer bookAuthorId) {
		this.bookAuthorId = bookAuthorId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
