package com.company.project1.dao;

import com.company.project1.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;
@Repository(value = "AuthorDao")
@Transactional
public class AuthorDaoImpl implements AuthorDao {
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


    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try{
            manager.getTransaction().begin();
            authors = manager.createNativeQuery("select * from authors").getResultList();
            manager.getTransaction().commit();
        }
        catch (Exception ex){
            logger.error("throwing error "+ex);
        }
        return authors;
    }

    @Override
    public Author getAuthorById(int id) {
       Author author = new Author();
        try{
            if (id>0){
                manager.getTransaction().begin();
                Query query = manager.createNativeQuery("select * from author where id=?");
                query.setParameter(1, id);
                 author = (Author)query.getSingleResult();
                manager.getTransaction().commit();
            }
        }
        catch (Exception ex){
            logger.error("throwing error "+ex);
            rollbackManager(manager);
        }
        finally {
            closeManager(manager);
        }
        return author;
    }

    @Override
    public void addingAuthor(Author author) {
        try{
            if (author.getName()!= null && author.getAge()>0 && author.getCountry()!=null){
                manager.getTransaction().begin();
                manager.persist(author);
                manager.getTransaction().commit();
            }
        }
        catch (Exception ex){
            logger.error("throwing error "+ex);
            rollbackManager(manager);
        }
        finally {
            closeManager(manager);
        }
    }

    @Override
    public void updatingAuthor(Author author) {
        try{
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery("select * from authors where name=? and age =? and country = ?");
            query.setParameter(1, author.getName());
            query.setParameter(2, author.getAge() );
            query.setParameter(3, author.getCountry());
            Author tmp = (Author)query.getSingleResult();
            manager.merge(tmp);
            manager.getTransaction().commit();
        }
        catch (Exception ex){
            rollbackManager(manager);
        }
        finally {
            closeManager(manager);
        }
    }

    @Override
    public void removingAuthor(int id) {
        try{
            manager.getTransaction().begin();
            Query query = manager.createNativeQuery("select * from authors where id =?");
            query.setParameter(1, id);
            Author author = (Author)query.getSingleResult();
            if (author!=null){
                manager.remove(author);
                manager.getTransaction().commit();
            }
        }
        catch (Exception ex){
            rollbackManager(manager);
        }
        finally {
            closeManager(manager);
        }
    }

    @Override
    public List<Author> searching(String name) {
        List<Author> authors = new ArrayList<>();
        try{
            manager.getTransaction().begin();
            authors = manager.createNativeQuery("select * from autrhors where name = ?").setParameter(1,name).getResultList();
        }
        catch (Exception ex){
            logger.error("throwing error "+ex);
            rollbackManager(manager);
        }
        finally {
            closeManager(manager);
        }
        return authors;
    }

    @Override
    public Author getAuthorByName(String name) {
        Author author = new Author();
        if (name!=null){
            try{
                manager.getTransaction().begin();
                author = (Author) manager.createNativeQuery("select * from authors where name=?").setParameter(1, name).getSingleResult();
            }
            catch (Exception ex){
                rollbackManager(manager);
            }
            finally {
                closeManager(manager);
            }
        }

        return author;
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
