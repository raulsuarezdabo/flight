
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import java.util.List;

/**
 * Interface for defining the methods to access to flight information
 * @author raulsuarez
 */
public interface FlightDAO {
    /**
     * Get all airplane
     * @return List
     */
    public List<FlightEntity> findAll();
    
    /**
     * Find airplane by id
     * @param id    int
     * @return  AirplaneEntity
     */
    public FlightEntity findById(int id);
    
    /**
     * Find flight by code
     * @param code  String
     * @return  FlightEntity   airport
     */
    public FlightEntity findByCode(String code);
    
    /**
     * Add new airplane
     * @param flight
     */
    public void addFlight(FlightEntity flight);
    
    /**
     * Update an airplane
     * @param flight
     * @return AirplaneEntity    returns the entity or null if error
     */
    public boolean updateFlight(FlightEntity flight);
    
    /**
     * Delete an airplane
     * @param flight
     * @return boolean  with success or not
     */
    public boolean deleteFlight(FlightEntity flight);
}
