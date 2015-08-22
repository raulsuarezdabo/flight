
package com.raulsuarezdabo.flight.service;

import com.mycompany.flight.service.EmailService;
import com.raulsuarezdabo.flight.dao.BookDAO;
import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to manage book layer logic
 * @author raulsuarez
 */
public class BookServiceImpl implements BookService {
    
    public static int DAYS = 60;
    
    @Autowired
    private FlightService flightService;
    
    @Autowired
    private BookDAO bookDAO;
    
    @Autowired
    private EmailService emailService;

    /**
     * Method to add his book
     * @param user  UserEntity
     * @param flight    FlightEntity
     * @param seats Set
     * @return  BookEntity
     */
    @Override
    @Transactional
    public BookEntity addBook(UserEntity user, FlightEntity flight, Set<SeatEntity> seats) {
        try {
            if (this.flightService.checkAvaliability(flight, seats) == false) {
                throw new Exception("Not available seats");
            }
            BookEntity book = new BookEntity();
            book.setFlight(flight);
            book.setUser(user);
            book.setStatus(BookEntity.CONFIM);
            this.bookDAO.addBook(book);
            if (this.flightService.addSeats(flight, seats, book) == false) {
                throw new Exception("Error! Imposible to add this seats");
            }
            // TODO: Add email notification with the confirmation of the book
            return book;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Method for adding more than one book proces
     * @param user  UserEntity
     * @param flights   List of flights
     * @param seats Set of seats
     * @return  List of books
     */
    @Override
    @Transactional
    public List <BookEntity> addBook(UserEntity user, List<FlightEntity> flights, Set <SeatEntity> seats) {
        try {
            List<BookEntity> books = new ArrayList();
            for (FlightEntity fligh: flights) {
                BookEntity temporalBook = this.addBook(user, fligh, seats);
                if (temporalBook == null) {
                    throw new Exception("Book transactional list fails");
                }
                books.add(temporalBook);
            }
            return books;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        } 
    }
    
    /**
     * Method to get books from this user
     * @param user  UserEntity
     * @return  List of books
     */
    @Override
    @Transactional
    public List<BookEntity> getBooks(UserEntity user) {
        return this.bookDAO.findByUser(user);
    }
    
    /**
     * Method to get books from this flight
     * @param flight    FlightEntity
     * @return  List of books
     */
    @Override
    @Transactional
    public List<BookEntity> getBooks(FlightEntity flight) {
        return this.bookDAO.findByFlight(flight);
    }
    
    /**
     * Method for returning a report for chart
     * @return  List
     */
    @Override
    public List getChart() {
        try {
            return this.bookDAO.findCountBooksByDate(BookServiceImpl.DAYS);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Get all books available
     * @return List all books listed
     */
    @Override
    public List<BookEntity> getAll() {
        try {
            List<BookEntity> books = this.bookDAO.findAll();
            return books;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Find book by id
     * @param id    int
     * @return  BookEntity
     */
    @Override
    public BookEntity getById(int id) {
        try {
            return this.bookDAO.findById(id);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
}
