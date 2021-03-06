/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
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
     * Method for getting books from a flight
     * @param flight    FlightEntity
     * @return  List of books
     */
    public List<BookEntity> findByFlight(FlightEntity flight);
    
    /**
     * Method to find list of books for tracking
     * @param days  int
     * @return  List
     */
    public List findCountBooksByDate(int days);
    
    /**
     * Method to get not confirmed books
     * @return  List of not confirmed books
     */
    public List findNotConfirmedBooks();
}
