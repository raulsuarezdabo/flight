
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.dao.SeatDAO;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for defining the interaction with the seats
 * @author raulsuarez
 */
@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatDAO seatDAO;

    /**
     * method for getting the list of seats
     * @return 
     */
    @Override
    public List<SeatEntity> getAll() {
        return this.seatDAO.findAll();
    }

    /**
     * method for getting the specific seat
     * @param id    primary key
     * @return  SeatEntity
     */
    @Override
    public SeatEntity getById(int id) {
        return this.seatDAO.findById(id);
    }
    
    /**
     * This method it's to count the number of seats used on a flight
     * @param flight
     * @param type
     * @return 
     */
    @Override
    public int numberSeatsUsed(FlightEntity flight, int type) {
        return this.seatDAO.countSeats(flight, type);
    }
    
}
