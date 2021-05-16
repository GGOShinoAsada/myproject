package com.company.project1.controller;

import com.company.project1.model.Author;
import com.company.project1.model.Book;
import com.company.project1.service.AuthorService;
import com.company.project1.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@RequestMapping("/books")
public class BooksController {
    private AuthorService authorService;
    private BookService bookService;
    @Autowired
    public void setBookService(BookService service){
        this.bookService = service;
    }
    @Autowired
    public void setAuthorService(AuthorService service){
        this.authorService = service;
    }
    @GetMapping("/index")
    public String getAllBooks(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "redirect: books/index";
    }
    @GetMapping("/new")
    public String addingBook(){
        return "books/new";
    }
    @PostMapping("/new")
    public String addingBookPost(@RequestParam("name") String name, @RequestParam("isbn") String isbn , @RequestParam("author") String author){
        boolean flag = true;
        if (name!= null && isbn!=null && author != null){
            Author tmp = authorService.getAuthorByName(author);
            if (author!=null){
                Book book = new Book(name,isbn, new Date(), tmp.getId(), tmp);
                bookService.addingBook(book);
            }
            else {
                flag = false;
            }
        }
        else
        {
            flag = false;
        }
       if (flag){
           return "redirect: books/";
       }
       else {
          return "books/new";
       }
    }
    @GetMapping("/details/{id}")
    public String getBookById(@RequestPart("id") int id, Model model){
        Book book = new Book();
        book = bookService.getBookById(id);
        Author author = authorService.getAuthorById(book.getAuthor_id());
        model.addAttribute("book",book);
        return "books/details";
    }
    @GetMapping("/edit/{id}")
    public String updatingBook(@RequestPart("id") int id, Model model){
        Book book = bookService.getBookById(id);
        Author author = authorService.getAuthorById(book.getAuthor_id());
        book.setAuthor(author);
        return "books/edit";
    }
   @PostMapping("/edit/{id}")
    public String updatingBook(@RequestPart("id") int id,@RequestParam("name") String name, @RequestParam("isbn") String isbn, @RequestParam("date") String date, @RequestParam("author") String author){
        boolean flag = true;
        if (name!=null && isbn != null && date != null && author  != null){
            try{
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                Date date1 = df.parse(date);
                Book book = bookService.getBookById(id);
                book.setDate(date1);
                book.setName(name);
                book.setIsbn(isbn);
                Author tmp = authorService.getAuthorByName(author);
                if (tmp!=null){
                    book.setAuthor(tmp);
                    bookService.updatingBook(book);
                }
                else {
                    flag = false;
                }
            }
            catch ( ParseException ex){
                flag = false;
                throw new NumberFormatException("please check input values");
            }

        }
        if (flag){
           return "redirect: books/";
        }
        else {
           return "books/edit";
        }
    }
    @GetMapping("/delete/{id}")
    public String removing(@RequestPart("id") int id){
        bookService.removingBook(id);
        return "redirect: books/index";
    }
}
