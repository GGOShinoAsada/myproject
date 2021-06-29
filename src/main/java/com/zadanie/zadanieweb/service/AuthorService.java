package com.zadanie.zadanieweb.service;

import com.zadanie.zadanieweb.model.Author;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public interface AuthorService {
    List<Author> getAllLists();
    Author getAuthorById(int id);
    void addingAuthor(Author au);
    void updatingAuthor(int auid, Author au);
    void  removingAuthor(int auid);
}
