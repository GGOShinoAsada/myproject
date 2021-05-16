package com.company.project1.dao;

import com.company.project1.model.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAllAuthors();
    Author getAuthorById(int id);
    void addingAuthor(Author author);
    void updatingAuthor(Author author);
    void removingAuthor(int id);
    List<Author> searching(String name);
    Author getAuthorByName(String name);
}
