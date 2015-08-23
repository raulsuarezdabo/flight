
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import java.util.List;
import java.util.Set;

/**
 * Service to manage book level logic
 * @author raulsuarez
 */
public interface BookService {
    
    /**
     * Method to add his book
     * @param user  UserEntity
     * @param flight    FlightEntity
     * @param seats Set
     * @return  BookEntity
     */
    public BookEntity addBook(UserEntity user, FlightEntity flight, Set<SeatEntity> seats);
    
    /**
     * Method for adding more than one book proces
     * @param user  UserEntity
     * @param flights   List of flights
     * @param seats Set of seats
     * @return  List of books
     */
    public List <BookEntity> addBook(UserEntity user, List <FlightEntity> flights, Set <SeatEntity> seats);
    
    /**
     * Method to get books from this user
     * @param user  UserEntity
     * @return  List of books
     */
    public List<BookEntity> getBooks(UserEntity user);
    
    /**
     * Method to get books from this flight
     * @param flight    FlightEntity
     * @return  List of books
     */
    public List<BookEntity> getBooks(FlightEntity flight);
    
    /**
     * Method for returning a report for chart
     * @return  List
     */
    public List getChart();
    
    /**
     * Get all books available
     * @return List all books listed
     */
    public List<BookEntity> getAll();
    
    /**
     * Find book by id
     * @param id    int
     * @return  BookEntity
     */
    public BookEntity getById(int id);
    
    /**
     * method for update book to confirm
     * @param book  BookEntity
     * @return  boolean
     */
    public BookEntity confirmBook(BookEntity book);
}
