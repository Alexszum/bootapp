package com.alena.litvinova.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alena.litvinova.dao.*;
import com.alena.litvinova.models.Book;
import com.alena.litvinova.models.BookForm;
import com.alena.litvinova.models.SearchForm;
import com.alena.litvinova.models.User;
import com.alena.litvinova.models.Writer;
import com.alena.litvinova.services.*;
import com.alena.litvinova.services.SearchStruct.Result;
import com.alena.litvinova.validator.UserValidator;

@Controller
public class MainController {
	
	
	
	    @Autowired
	    private UserService userService;

	    @Autowired
	    private SecurityService securityService;

	    @Autowired
	    private UserValidator userValidator;
	    
	    
	    
	    
	@Autowired
	MainService service;
	
    @GetMapping("/signup")
    public String showSignUpForm() {
        return "signup";
    }
    
    
    
    @GetMapping("/index")
	public String indexPage(Model model) {
		model.addAttribute("search", new SearchForm());
		//model.addAttribute("message", "my msg!!!!!!!!!");
		//model.addAttribute("writers", service.getWritters());
		model.addAttribute("books", service.getBooks());
		model.addAttribute("service", service);
		return "index";
	} 
    @GetMapping("/writers")
	public String writersPage(Model model) {
		model.addAttribute("writers", service.getWritters());
		return "writers";
	}    
    @GetMapping("/books")
	public String bookssPage(Model model) {
		model.addAttribute("books", service.getBooks());
		return "books";
	}
    @RequestMapping("/delete/book")
    public String deleteBook(@RequestParam("id") Integer id, Model model) {
    	
    	service.deleteBook(id);
        return "redirect:../books";
    }
    @RequestMapping("/delete/writer")
    public String deleteWriter(@RequestParam("id") Integer id, Model model) {
    	
    	service.deleteWriter(id);
        return "redirect:../writers";
    }
    
    
    @RequestMapping("/search")
	public String searchPage(@RequestParam("data") String data,Model model) {
		model.addAttribute("search", new SearchForm());
		model.addAttribute("service", service);
		String searchmsg = "Ничего не найдено..";
		SearchStruct search = new SearchStruct();
		search = service.searchByData(data);
		List<Book> books = new ArrayList<Book>();
		if(search.result== Result.WRITER_FOUND) {
			searchmsg = "Результаты поиска:";
			for(Book book: service.getBooks()) {
				if(book.getBookAuthorId().equals(search.id)) {
					books.add(book);
				}
			}
		}
		else if(search.result== Result.BOOK_FOUND) {
			searchmsg = "Результаты поиска:";
			for(Integer i : search.bookIds) {
				books.add(service.findABookById(i));
			}
		}

		model.addAttribute("searchmsg", searchmsg);
		model.addAttribute("books", books);
		return "search";
	}
    @RequestMapping(value = "/search/submit", method = RequestMethod.POST)
    public String searchData(@ModelAttribute SearchForm searchform, RedirectAttributes redirectAttributes) {
    	redirectAttributes.addAttribute("data", searchform.getSearchRequest());
        return "redirect:../search";
    }
    
    
    @RequestMapping("/bookinfo")
    public String bookInfo(@RequestParam("id") Integer id, @RequestParam("authorid") Integer authorid,
    		 @RequestParam("author") String author, Model model) {
    	Book book =  service.getBook(id);
    	model.addAttribute("messages", "mememe mememe!!!!!!");
    	model.addAttribute("author", "Автор: "+ author);
    	model.addAttribute("bookname", "Книга: " + book.getBookName());
    	model.addAttribute("description", book.getBookDescription());
    	model.addAttribute("price", book.getPrice() + " $");
    	
        return "bookinfo";
    }

    @RequestMapping(value = "/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new BookForm());
		model.addAttribute("writers", service.getWritters());
        return "addbook";
    } 
    @RequestMapping(value = "/addwriter")
    public String addwriter(Model model) {
        model.addAttribute("writer", new Writer());
        return "addwriter";
    }
    
    @RequestMapping(value = "/updatebook")
    public String updateBookPage(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("book", service.findABookById(id));
		model.addAttribute("writers", service.getWritters());
        return "updatebook";
    }

    @RequestMapping(value = "/addbook/submit", method = RequestMethod.POST)
    public String submitBook(@ModelAttribute BookForm bookform) {
    	Book book = new Book();
    	book.setBookName(bookform.getBookName());
    	book.setBookDescription(bookform.getBookDescription());
    	book.setPrice(bookform.getPrice());
    	book.setBookAuthorId(bookform.getBookAuthorId());
        service.saveBook(book);
        return "redirect:../index";
    }
    
    @RequestMapping(value = "/addwriter/submit", method = RequestMethod.POST)
    public String submitWriter(@ModelAttribute Writer writer) {
        service.saveWriter(writer);
        return "redirect:../index";
    }
    
    @RequestMapping(value = "/updatebook/submit", method = RequestMethod.POST)
    public String updateBook(@RequestParam("id") Integer id, @ModelAttribute Book book) {
    	book.setId(id);
        service.saveBook(book);
        return "redirect:../books";
    }
//user controler
    
    @GetMapping("/registration") //сюда попадаем когда просто заходим на страницу (метод GetMapping
    public String registration(Model model) {
        model.addAttribute("userForm", new User()); //в html страницу внедряем обьект класса "User" с именем "userForm" (который потом заполняем в формачках страницы

        return "registration";
    }

    @PostMapping("/registration") //сюда попадаем когда на странице нажимаем кнопку "зарегистрироваться" (метод PostMapping)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {//из формочки пришел заполненный обьект "userForm"
        userValidator.validate(userForm, bindingResult); //проверяем его на валидность

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/index";
    }

    @GetMapping("/signin")
    public String login(Model model, String error, String logout) {
        model.addAttribute("userForm", new User());
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "signin";
    }
    
//    @GetMapping({"/", "/index"})
//    public String welcome(Model model) {
//        return "index";
//    }
}

    
    
    
    
    
//    @GetMapping("/auth")
//	public String auth(Model model) {
//    	
//		return "auth";
//	}  
//    // my cod
//    @GetMapping("/registration")
//    public String showRegistrationForm() {
//        return "registration";
//    }
//    @GetMapping("/signin")
//    public String showSignin() {
//        return "signin";
//    }
//    // additional CRUD methods

