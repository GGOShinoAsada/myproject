package com.zadanie.zadanieweb.dao;

import com.zadanie.zadanieweb.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class AuthorDaoImpl implements AuthorDao {
@Autowired
private SessionFactory factory;

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList();
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            authors = session.createSQLQuery("select * from authors").addEntity(Author.class).getResultList();
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public Author getAuthorById(int id) {
        Author author = new Author();
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            author = session.get(Author.class, id);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public void addingAuthor(Author author) {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            session.save(author);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatingAuthor(int auid, Author au) {
        try {
            Session session = factory.openSession();
            session.getTransaction().begin();
            Author author = session.get(Author.class, auid);
            author.setCountry(au.getCountry());
            author.setName(au.getName());
            session.update(author);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removingAuthor(int authorid) {
        try{
            Session session = factory.openSession();
            session.getTransaction().begin();
            session.remove(session.get(Author.class, authorid));
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
