package com.company.project1.service;

import com.company.project1.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author getAuthorById(int id);
    void addingAuthor(Author au);
    void removingAuthor(int id);
    void updatingAuthor(Author au);
    List<Author> searching(String name);
    Author getAuthorByName(String name);
}
