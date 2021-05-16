package com.company.project1.dao;

import com.company.project1.model.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAllBooks();
    Book getBookById(int id);
    void addingBook(Book b);
    void removingBook(int id);
    void updatingBook(Book b);
}
