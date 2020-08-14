package com.alena.litvinova.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alena.litvinova.models.Book;

@Repository
public interface IBookCRUD extends CrudRepository<Book, Integer> {

}
