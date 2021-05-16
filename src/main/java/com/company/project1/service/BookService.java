package com.company.project1.service;

import com.company.project1.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(int id);
    void addingBook(Book book);
    void removingBook(int id);
    void updatingBook(Book b);
}
