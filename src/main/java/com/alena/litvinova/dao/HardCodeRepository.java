package com.alena.litvinova.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.alena.litvinova.models.Book;
import com.alena.litvinova.models.Writer;

@Repository
public class HardCodeRepository {

	public List<Writer> writers = new ArrayList<Writer>();
	
	public List<Book> books = new ArrayList<Book>();
	
	HardCodeRepository(){
		writers.add(new Writer("Николай", "Гоголь"));
		writers.add(new Writer("Льюис", "Кэрролл"));
		writers.add(new Writer("Джордж", "Оруэлл"));
		
		books.add(new Book(1, "Алиса в Стране чудес", "Приключения Алисы в Стране чудес", 2 , 280 ));
		books.add(new Book(2, "Охота на Снарка", "Охота команды из девяти человек и бобра за таинственным Снарком", 2 , 320 ));
		books.add(new Book(3,"История с узелками", "Одна из образцовых книг по математике", 2 , 266 ));
		

		books.add(new Book(4, "Мертвые души", "Приключения Алисы в Стране чудес", 1 , 175 ));
		books.add(new Book(5, "Ревизор", "Приключения Алисы в Стране чудес", 1 , 427 ));
		books.add(new Book(6, "Вий", "Приключения Алисы в Стране чудес", 1 , 333 ));	
		
		books.add(new Book(7, "1984", "Про тоталитарный режим и методы управления людьми", 3 , 445 ));
		books.add(new Book(8, "Фунты лиха в Париже и Лондоне", "Повесть, основанная на реальных событиях в жизни автора", 3 , 530 ));
		books.add(new Book(9, "Дни в Бирме", "Роман основаный на автобиографическом материале", 3, 234 ));
	}
	
	public List<Writer> getAllWriters(){
		return writers;
	}
	
	public List<Book> getAllBooks(){
		return books;
	}
	
}
