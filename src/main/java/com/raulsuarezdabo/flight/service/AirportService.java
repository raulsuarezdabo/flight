package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.List;

/**
 * Interface for defining the methods to access for the data
 * @author raulsuarez
 */
public interface AirportService {
    /**
     * Method for adding new airport
     * @param name  String
     * @param code  String
     * @param country   CountryEntity
     * @param city  CityEntity
     * @return  AirportEntity
     */
    public AirportEntity addAirport(String name, String code, CountryEntity country, CityEntity city);
    
    /**
     * Method for update the airport information
     * @param id    int
     * @param airport   AirportEntity   Information to update
     * @param update    boolean indicates if persist or not
     * @return AirportEntity    airport updated or null if some problem
     */
    public AirportEntity updateAirport(int id, AirportEntity airport, boolean update);
    
    /**
     * Method to delete an airport
     * @param id    int identifier of the aiport
     * @return boolean  if it's success or not
     */
    public boolean deleteAirport(int id);
    
    /**
     * Get all airports available
     * @return List all airports listed
     */
    public List<AirportEntity> getAll();
    
    /**
     * Get an airport by code
     * @param code  String  code
     * @return  AirportEntity   airport
     */
    public AirportEntity getByCode(String code);
    
    /**
     * Get an airport by id
     * @param id    int
     * @return AirportEntity
     */
    public AirportEntity getById(int id);
    
}
