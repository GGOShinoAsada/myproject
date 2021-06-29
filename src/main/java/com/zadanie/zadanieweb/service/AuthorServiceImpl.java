package com.zadanie.zadanieweb.service;

import com.zadanie.zadanieweb.dao.AuthorDao;
import com.zadanie.zadanieweb.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDao dao;
    @Override
    public List<Author> getAllLists() {
        return dao.getAllAuthors();
    }

    @Override
    public Author getAuthorById(int id) {
        return dao.getAuthorById(id);
    }

    @Override
    public void addingAuthor(Author au) {
        dao.addingAuthor(au);
    }

    @Override
    public void updatingAuthor(int auid, Author au) {
        dao.updatingAuthor(auid, au);
    }

    @Override
    public void removingAuthor(int auid) {
        dao.removingAuthor(auid);
    }
}
