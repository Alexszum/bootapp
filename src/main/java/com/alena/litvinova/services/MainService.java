package com.alena.litvinova.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alena.litvinova.dao.*;
import com.alena.litvinova.models.*;
import com.alena.litvinova.services.SearchStruct.Result;


@Service
public class MainService {
	
	@Autowired
	private HardCodeRepository hardCodeDao;
	
	@Autowired
	private IWritterCRUD writersCRUD;
	
	@Autowired
	private IBookCRUD booksCRUD;

	public List<Writer> getAllWritters() {
		return hardCodeDao.getAllWriters();
	}	
	
	public List<Book> getAllBooks() {
		return hardCodeDao.getAllBooks();
	}
	
	public List<Writer> getWritters() {
		return (List<Writer>)writersCRUD.findAll();
	}	
	
	public List<Book> getBooks() {
		return (List<Book>)booksCRUD.findAll();
	}
	
	public Book getBook(Integer id) {
		return booksCRUD.findById(id).get();
	}	
	
	public void saveBook(Book book) {
		booksCRUD.save(book);
	}
	
	public void saveWriter(Writer writer) {
		writersCRUD.save(writer);
	}
	
	public String findAuthor(Integer id) { 
		String author = writersCRUD.findById(id).get().getFirstName();
		author+= " ";
		author += writersCRUD.findById(id).get().getLastName();
		return author;
	}
	
	public Writer findAuthorById(Integer id) { 
		return writersCRUD.findById(id).get();
	}	
	public Book findABookById(Integer id) { 
		return booksCRUD.findById(id).get();
	}
	
	public SearchStruct searchByData(String data) {
		SearchStruct search = new SearchStruct();

		String[] splited = data.toLowerCase().split(" ");
		Boolean writerFound = false;
		Boolean bookFound = false;
		
		for (String s: splited) {
			if(bookFound){break;}
			
			for(Writer w: getWritters()) {
				if(w.getFirstName().toLowerCase().contains(s) || w.getLastName().toLowerCase().contains(s) ) {
					search.result = Result.WRITER_FOUND;
					search.id = w.getId();
					writerFound = true;
					break;
				}
			}
			
			if(writerFound){break;}
			
			for(Book b: getBooks()) {
				if(b.getBookName().toLowerCase().contains(s)) {
					search.result = Result.BOOK_FOUND;
					search.bookIds.add(b.getId());
					bookFound = true;
				}
			}
		}
		return search;
	}
	
	public void deleteBook(Integer id) {
		booksCRUD.deleteById(id);
	}	
	
	public void deleteWriter(Integer id) {
		for(Book b: getBooks()) {
			if(b.getBookAuthorId().equals(id)) {
				booksCRUD.deleteById(b.getId());
			}
		}
		writersCRUD.deleteById(id);
	}
	
	public void updateBook(Book book) {
		booksCRUD.save(book);
	}
}

