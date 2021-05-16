package com.company.project1.service;

import com.company.project1.dao.AuthorDao;
import com.company.project1.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService {
    AuthorDao dao;
    @Autowired
    public void setAuthorDao(AuthorDao au){
        dao = au;
    }
    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authors = dao.getAllAuthors();
        return authors;
    }

    @Override
    public Author getAuthorById(int id) {
        Author author = new Author();
        author = dao.getAuthorById(id);
        return author;
    }

    @Override
    public void addingAuthor(Author au) {
        dao.addingAuthor(au);
    }

    @Override
    public void removingAuthor(int id) {
        dao.removingAuthor(id);
    }

    @Override
    public void updatingAuthor(Author au) {
        dao.updatingAuthor(au);
    }

    @Override
    public List<Author> searching(String name) {
        List<Author> authors = dao.searching(name);
        return authors;
    }

    @Override
    public Author getAuthorByName(String name) {
        Author author = dao.getAuthorByName(name);
        return  author;
    }
}
