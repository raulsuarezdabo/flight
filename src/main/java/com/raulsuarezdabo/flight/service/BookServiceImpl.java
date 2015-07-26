
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.dao.BookDAO;
import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import java.util.Date;
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
            return book;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
