package com.company.project1.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity(name = "author")
public class Author {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   @Column(name = "name", nullable = false)
   private String name;
   @Column(name = "country", nullable = false)
   private String country;
   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   List<Book> books = new ArrayList<>();

    public Author(String name, String country, int age) {
        this.name = name;
        this.country = country;
        this.age = age;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Author() {
    }
    @Column(name = "age", nullable = false)
    private int age;
}
