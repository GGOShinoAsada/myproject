package com.zadanie.zadanieweb.dao;

import com.zadanie.zadanieweb.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
@Primary
public class BookDaoImpl implements BookDao {
    @Autowired
    private SessionFactory factory;
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList();
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            books = session.createSQLQuery("select * from book ").addEntity(Book.class).getResultList();
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getBookById(int id) {
        Book b = new Book();
        try{
            Session se = factory.openSession();
            se.getTransaction().begin();
            b = se.get(Book.class, id);
            se.getTransaction().commit();
            se.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public void addingBook(Book b) {
        try{
            Session se = factory.openSession();
            se.getTransaction().begin();
            se.save(b);
            se.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatingBook(int bold, Book bnew) {
        try{
            Session se = factory.openSession();
            se.getTransaction().begin();
            Book book = se.get(Book.class, bold);
            if (book!=null){
                book.setName(bnew.getName());
                book.setAuthor_id(bnew.getAuthor_id());
                book.setIsbn(bnew.getIsbn());
            }
            se.getTransaction().commit();
            se.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removingBook(int bid) {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            session.remove(session.get(Book.class, bid));
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
