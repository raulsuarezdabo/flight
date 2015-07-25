
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.dao.SeatDAO;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for defining the interaction with the seats
 * @author raulsuarez
 */
@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatDAO seatDAO;

    /**
     * method for adding new seat
     * @param type  class of the seat
     * @param flight    flight it comes from the seat
     * @return SeatEntity
     */
    @Override
    @Transactional
    public SeatEntity addSeat(int type, FlightEntity flight) {
        try {
        SeatEntity seat = new SeatEntity();
        
        seat.setType(type);
        seat.setFlight(flight);
        this.seatDAO.addSeat(seat);
        return seat;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Update Seat
     * @param id    primary key
     * @param seat  Seat with the values for update
     * @param update    boolean for updating
     * @return  Success or not updating
     */
    @Override
    @Transactional
    public SeatEntity updateSeat(int id, SeatEntity seat, boolean update) {
        try {
            SeatEntity seatToUpdate = this.seatDAO.findById(id);
            if (seat.getType() != 0) {
                seatToUpdate.setType(seat.getType());
            }
            if (seat.getFlight() != null) {
                seatToUpdate.setFlight(seat.getFlight());
            }
            
            if (this.seatDAO.updateSeat(seatToUpdate) == false) {
                throw new Exception("Error updating the airline");
            }
            return seatToUpdate;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * method for deleting the seat
     * @param id
     * @return  boolean success or not
     */
    @Override
    @Transactional
    public boolean deleteSeat(int id) {
        try {
            SeatEntity seat = this.seatDAO.findById(id);
            if (seat == null) {
                throw new Exception("Not found airline");
            }
            return this.seatDAO.deleteSeat(seat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

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
