
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import java.util.List;

/**
 * Interface for defining the methods to access to seat information
 * @author raulsuarez
 */
public interface SeatDAO {
    /**
     * Get all seats
     * @return List
     */
    public List<SeatEntity> findAll();
    
    /**
     * Find seat by id
     * @param id    int
     * @return  SeatEntity
     */
    public SeatEntity findById(int id);
    
    /**
     * Add new seat
     * @param seat
     */
    public void addSeat(SeatEntity seat);
    
    /**
     * Update a seat
     * @param seat  SeatEntity
     * @return SeatEntity    returns the entity or null if error
     */
    public boolean updateSeat(SeatEntity seat);
    
    /**
     * Delete a seat
     * @param seat
     * @return boolean  with success or not
     */
    public boolean deleteSeat(SeatEntity seat);
    
    /**
     * Count the number of results from the query
     * @param flight    FlightEntity
     * @param type  int 
     * @return  int
     */
    public int countSeats(FlightEntity flight, int type);
}
