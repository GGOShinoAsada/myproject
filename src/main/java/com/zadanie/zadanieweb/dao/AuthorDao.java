package com.zadanie.zadanieweb.dao;

import com.zadanie.zadanieweb.model.Author;
import java.util.*;


public interface AuthorDao {
    List<Author> getAllAuthors();
    Author getAuthorById(int id);
    void addingAuthor(Author author);
    void updatingAuthor(int auid, Author au);
    void removingAuthor(int authorid);
}
