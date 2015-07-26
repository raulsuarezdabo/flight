
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.BookEntity;
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
}
