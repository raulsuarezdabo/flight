
package com.raulsuarezdabo.flight.service;

import com.mycompany.flight.service.EmailService;
import com.raulsuarezdabo.flight.dao.BookDAO;
import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to manage book layer logic
 * @author raulsuarez
 */
public class BookServiceImpl implements BookService {
    
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
            if (this.flightService.addSeats(flight, seats) == false) {
                throw new Exception("Error! Imposible to add this seats");
            }
            BookEntity book = new BookEntity();
            book.setFlight(flight);
            book.setCreatedAt(new Date());
            book.setUser(user);
            book.setStatus(BookEntity.CONFIM);
            this.bookDAO.addBook(book);
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
    
}
