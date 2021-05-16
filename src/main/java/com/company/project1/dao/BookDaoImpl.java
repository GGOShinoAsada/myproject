package com.company.project1.dao;

import com.company.project1.model.Author;
import com.company.project1.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Repository(value = "BookDao")
@Transactional
public class BookDaoImpl implements BookDao {
    private BookDao dao;
    Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);
    @PersistenceContext
    EntityManager manager;
    @Autowired
    private void setEntityManager(EntityManager em){
        manager = em;
    }

    @Autowired
    private void setBookDao(BookDao bd){
        this.dao = bd;
    }
    private EntityManager em;
    private void setEntityManager(DataSource ds){

    }
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try{
            manager.getTransaction().begin();
            books =  manager.createNativeQuery("select * from books").getResultList();
            manager.getTransaction().commit();
        }
        catch (Exception ex){
            logger.debug("throwing exception "+ex);
        }
        return books;
    }

    @Override
    public Book getBookById(int id) {
        Book book = new Book();
        try{
            manager.getTransaction().begin();
            Query query =  manager.createNativeQuery("select * from books where id=?");
            query.setParameter(1, id);
            book = (Book) query.getSingleResult();
            manager.getTransaction().commit();
        }
        catch (Exception ex){
            logger.error("throwing error"+ex);
        }
        return book;
    }

    @Override
    public void addingBook(Book b) {
        try{
            if (b.getName()!=null && b.getDate()!=null){
                manager.getTransaction().begin();
                manager.persist(b);
                manager.getTransaction().commit();
            }

        }
        catch (Exception ex){
            rollbackManager(em);
            logger.error("throwing exception "+ex);
        }
        finally {
            closeManager(em);
        }
    }

    @Override
    public void removingBook(int id) {
        try {
            if (id>0){
                manager.getTransaction().begin();
                Book b = getBookById(id);
                manager.remove(b);
                manager.getTransaction().commit();

            }
        }
        catch (Exception ex){
            rollbackManager(em);
            logger.debug("throwing exception"+ex);
        }
        finally {
            closeManager(em);
        }
    }

    @Override
    public void updatingBook(Book b) {
        try{
            if (b.getName()!=null && b.getDate()!=null){
                manager.getTransaction().begin();
                Book book = (Book) manager.createNativeQuery("select * from books where name = ? and description = ?").getSingleResult();
                book.setName(b.getName());
                book.setDate(b.getDate());
                manager.merge(book);
                manager.getTransaction().commit();
            }
        }
        catch (Exception ex){
            logger.error("throwing exception "+ex);
            rollbackManager(em);
        }
        finally {
            closeManager(em);
        }
    }
    private void closeManager(EntityManager em){
        if (em.isOpen()){
            em.flush();
            em.close();
        }
    }
    private void rollbackManager(EntityManager em){
        if (em.isOpen()){
            em.getTransaction().rollback();
            em.flush();
            em.close();
        }
    }
}
