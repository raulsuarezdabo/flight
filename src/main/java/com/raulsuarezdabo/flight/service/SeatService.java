
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import java.util.List;

/**
 *
 * @author raulsuarez
 */
public interface SeatService {
    /**
     * Method for adding new seat
     * @param type
     * @param flight
     * @return  SeatEntity
     */
    public SeatEntity addSeat(int type, FlightEntity flight);
    
    /**
     * Method for update the seat information
     * @param id    int
     * @param seat   SeatEntity   Information to update
     * @param update    boolean indicates if persist or not
     * @return SeatEntity    seat updated or null if some problem
     */
    public SeatEntity updateSeat(int id, SeatEntity seat, boolean update);
    
    /**
     * Method to delete an seat
     * @param id    int identifier of the aiport
     * @return boolean  if it's success or not
     */
    public boolean deleteSeat(int id);
    
    /**
     * Get all seats available
     * @return List all seat listed
     */
    public List<SeatEntity> getAll();
    
    /**
     * Get an seat by id
     * @param id    int
     * @return SeatEntity
     */
    public SeatEntity getById(int id);
    
     /**
     * This method it's to count the number of seats used on a flight
     * @param flight
     * @param type
     * @return 
     */
    public int numberSeatsUsed(FlightEntity flight, int type);
}
