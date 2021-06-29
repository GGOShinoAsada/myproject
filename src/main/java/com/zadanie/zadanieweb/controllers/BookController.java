package com.zadanie.zadanieweb.controllers;

import com.zadanie.zadanieweb.model.Author;
import com.zadanie.zadanieweb.model.Book;
import com.zadanie.zadanieweb.service.AuthorService;
import com.zadanie.zadanieweb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.util.*;
@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService service;
    @Autowired
    private AuthorService ser;
    @GetMapping("/index")
    private String index(Model model) {
        model.addAttribute("books", service.getAllBooks());
        return "books/index";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") int id, Model model){
        Book b = service.getBookById(id);
        b.setAuthor(ser.getAuthorById(b.getAuthor_id()));
        model.addAttribute("book", b );
        //model.addAttribute("author", ser.getAuthorById(b.getAuthor_id()));
        return "books/details";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("authors", ser.getAllLists());
        return "books/create";
    }
    @PostMapping("/create")
    public String createPost(@Valid String authors, Book book){
        if (authors!=""){
            int auid = Integer.parseInt(authors);
            book.setAuthor_id(auid);
            book.setAuthor(ser.getAuthorById(auid));
            service.addingBook(book);
        }
        else {
            //do something
        }


        return "redirect:/books/index";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", service.getBookById(id));
        model.addAttribute("authors", ser.getAllLists());
        return "books/edit";
    }
    @PostMapping("/edit")
    public String editpost(@Valid String authors, Book book){
        if (authors!=""){
            Integer auid = Integer.parseInt(authors);
            book.setAuthor_id(auid);
            book.setAuthor(ser.getAuthorById(auid));
            service.updatingBook(book.getId(), book);
        }
       else {
           //do something
        }
        return "redirect:/books/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteget(@PathVariable("id") int id){
        service.removingBook(id);
        return "redirect:/books/index";
    }

}
