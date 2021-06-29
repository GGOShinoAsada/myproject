package com.zadanie.zadanieweb.dao;

import com.zadanie.zadanieweb.model.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAllBooks();
    Book getBookById(int id);
    void addingBook(Book b);
    void updatingBook(int bold, Book bnew);
    void removingBook(int bid);
}
