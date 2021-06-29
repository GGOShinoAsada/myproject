package com.zadanie.zadanieweb.service;

import com.zadanie.zadanieweb.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(int id);
    void addingBook(Book b);
    void updatingBook(int bid, Book b);
    void removingBook(int bid);
}
