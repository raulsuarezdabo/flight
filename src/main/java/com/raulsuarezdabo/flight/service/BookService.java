
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
}
