package com.zadanie.zadanieweb.service;

import com.zadanie.zadanieweb.dao.BookDao;
import com.zadanie.zadanieweb.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
   @Autowired
    private BookDao dao;

    @Override
    public List<Book> getAllBooks() {
        return dao.getAllBooks();
    }

    @Override
    public Book getBookById(int id) {
        return dao.getBookById(id);
    }

    @Override
    public void addingBook(Book b) {
        dao.addingBook(b);
    }

    @Override
    public void updatingBook(int bid, Book b) {
        dao.updatingBook(bid, b);
    }

    @Override
    public void removingBook(int bid) {
        dao.removingBook(bid);
    }
}
