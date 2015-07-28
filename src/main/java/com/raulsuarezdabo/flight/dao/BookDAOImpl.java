
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository for defining the methods to access to book information
 * @author raulsuarez
 */
@Repository
public class BookDAOImpl implements BookDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Getter of the SessionFactory
     *
     * @return SessionFactory
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Setter sessionFactory
     *
     * @param entityManager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Get all Book
     * @return List
     */
    @Override
    public List<BookEntity> findAll() {
        return this.entityManager.createQuery("from BookEntity").getResultList();
    }

    /**
     * Find book by id
     * @param id    int
     * @return  BookEntity
     */
    @Override
    public BookEntity findById(int id) {
        try {
            return (BookEntity) this.entityManager.find(BookEntity.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Add new airport
     * @param book   BookEntity
     */
    @Override
    public void addBook(BookEntity book) {
        this.entityManager.persist(book);
    }

    /**
     * Update an airport
     * @param book   BookEntity
     * @return BookEntity    returns the entity or null if error
     */
    @Override
    public boolean updateBook(BookEntity book) {
        try {
            this.entityManager.merge(book);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Delete an airport
     * @param book   BookEntity
     * @return boolean  with success or not
     */
    @Override
    public boolean deleteBook(BookEntity book) {
        try {
            this.entityManager.remove(book);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    /**
     * Method for getting books from a user
     * @param user  UserEntity
     * @return  List of books
     */
    @Override
    public List<BookEntity> findByUser(UserEntity user) {
        try {
            List<BookEntity> books = new ArrayList();
            Query query =  this.entityManager.createQuery("from BookEntity where user = :user");
            query.setParameter("user", user);
            return query.getResultList();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
