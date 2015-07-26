
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import java.util.Set;

/**
 * Service to manage book level logic
 * @author raulsuarez
 */
public interface BookService {
    
    public BookEntity addBook(UserEntity user, FlightEntity flight, Set<SeatEntity> seats);
}
