package com.company.project1.service;

import com.company.project1.dao.BookDao;
import com.company.project1.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BookServiceImpl implements BookService{

    private BookDao dao;
    @Autowired
    private void setBookDao(BookDao bd){
        this.dao = bd;
    }
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        books = dao.getAllBooks();
        return books;
    }

    @Override
    public Book getBookById(int id) {
        Book b = dao.getBookById(id);
        return b;
    }

    @Override
    public void addingBook(Book book) {
        dao.addingBook(book);
    }

    @Override
    public void removingBook(int id) {
        dao.removingBook(id);
    }

    @Override
    public void updatingBook(Book b) {
        dao.updatingBook(b);
    }
}
