package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.AirportEntity;
import java.util.List;

/**
 * Interface for defining the methods to access to airport information
 * @author raulsuarez
 */
public interface AirportDAO {
   
    /**
     * Get all airports
     * @return List
     */
    public List<AirportEntity> findAll();
    
    /**
     * Find airport by code
     * @param code  String
     * @return  AirportEntity   airport
     */
    public AirportEntity findByCode(String code);
    
    /**
     * Find airport by id
     * @param id    int
     * @return  AirportEntity
     */
    public AirportEntity findById(int id);
    
    /**
     * Add new airport
     * @param airport   AirportEntity
     */
    public void addAirport(AirportEntity airport);
    
    /**
     * Update an airport
     * @param airport   AirportEntity
     * @return AirportEntity    returns the entity or null if error
     */
    public boolean updateAirport(AirportEntity airport);
    
    /**
     * Delete an airport
     * @param airport   AirportEntity
     * @return boolean  with success or not
     */
    public boolean deleteAirport(AirportEntity airport);
}
