
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import java.util.List;

/**
 * Interface for defining the methods to access to book information
 * @author raulsuarez
 */
public interface BookDAO {
    /**
     * Get all books
     * @return List
     */
    public List<BookEntity> findAll();
    
    /**
     * Find book by id
     * @param id    int
     * @return  BookEntity
     */
    public BookEntity findById(int id);
    
    /**
     * Add new airport
     * @param book   BookEntity
     */
    public void addBook(BookEntity book);
    
    /**
     * Update an book
     * @param book   AirportEntity
     * @return BookEntity    returns the entity or null if error
     */
    public boolean updateBook(BookEntity book);
    
    /**
     * Delete a book
     * @param book   BookEntity
     * @return boolean  with success or not
     */
    public boolean deleteBook(BookEntity book);
    
    /**
     * Method for getting books from a user
     * @param user  UserEntity
     * @return  List of books
     */
    public List<BookEntity> findByUser(UserEntity user);
    
    /**
     * Method to find list of books for tracking
     * @param days  int
     * @return  List
     */
    public List findCountBooksByDate(int days);
}
