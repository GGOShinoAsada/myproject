package com.company.project1.model;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Date;
@Entity(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "ISBN", nullable = false)
    private String isbn;

    public Book(String name, String isbn, Date date, int author_id, Author author) {
        this.name = name;
        this.isbn = isbn;
        this.date = date;
        this.author_id = author_id;
        this.author = author;
    }

    public Book(String name, String isbn, Date date, int author_id) {
        this.name = name;
        this.isbn = isbn;
        this.date = date;
        this.author_id = author_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "author", nullable = false)
    private int author_id;

    @OneToMany(fetch = FetchType.LAZY)
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Book() {
    }
}
