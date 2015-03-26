
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import java.util.Date;
import java.util.List;

/**
 * Interface for defining the methods to implments that solve the necessities
 * @author raulsuarez
 */
public interface FlightService {
    
    /**
     * Method for adding new flight
     * @param airportFrom   Airportfrom where it comes from
     * @param airportTo AirportTo   where it goes
     * @param Start Date    datetime takes off
     * @param time  int    time for arriving 
     * @param airplane  AirplaneEntity  airplane
     * @return  FlightEntity    flight created
     */
    public FlightEntity addFlight(AirportEntity airportFrom, AirportEntity airportTo, Date Start, Date time, AirplaneEntity airplane);
    
    /**
     * Method to update the flight
     * @param id    int reference of the flight
     * @param flight    FlightEntity    flight to update
     * @param update    boolean to persist or not
     * @return  FlightEntity
     */
    public FlightEntity updateFlight(int id, FlightEntity flight, boolean update);
    
    /**
     * Method to delete a flight
     * @param id    int identifier of the aiport
     * @return boolean  if it's success or not
     */
    public boolean deleteFlight(int id);
    
    /**
     * Get all flight available
     * @return List all flight listed
     */
    public List<FlightEntity> getAll();
    
    /**
     * Get a flight by code
     * @param code  String  code
     * @return  FlightEntity   airport
     */
    public FlightEntity getByCode(String code);
    
    /**
     * Get a flight by id
     * @param id    int
     * @return FlightEntity
     */
    public FlightEntity getById(int id);
}
