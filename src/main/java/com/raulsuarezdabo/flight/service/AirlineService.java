package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.entity.AirlineEntity;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.List;

/**
 * Interface for defining the methods to access for the data
 * @author raulsuarez
 */
public interface AirlineService {
    /**
     * Method for adding new airline
     * @param name  String
     * @param code  String
     * @param country   CountryEntity
     * @return  AirlineEntity
     */
    public AirlineEntity addAirline(String name, String code, CountryEntity country);
    
    /**
     * Method for update the airline information
     * @param id    int
     * @param airline   AirlineEntity   Information to update
     * @param update    boolean indicates if persist or not
     * @return AirlineEntity    airline updated or null if some problem
     */
    public AirlineEntity updateAirline(int id, AirlineEntity airline, boolean update);
    
    /**
     * Method to delete an airline
     * @param id    int identifier of the aiport
     * @return boolean  if it's success or not
     */
    public boolean deleteAirline(int id);
    
    /**
     * Get all airlines available
     * @return List all airlines listed
     */
    public List<AirlineEntity> getAll();
    
    /**
     * Get an airline by code
     * @param code  String  code
     * @return  AirlineEntity   airline
     */
    public AirlineEntity getByCode(String code);
    
    /**
     * Get an airline by id
     * @param id    int
     * @return AirlineEntity
     */
    public AirlineEntity getById(int id);
    
}
