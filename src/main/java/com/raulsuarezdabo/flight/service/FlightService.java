
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    
    /**
     * Method for searching a flight with the specific criteria
     * @param from  CityEntity
     * @param to    CityEntity
     * @param when  Date
     * @param numPassengers int
     * @return  List of results of flight found by this criteria
     */
    public List <FlightEntity> searchFlights(CityEntity from, CityEntity to, Date when, int numPassengers);
    
    /**
     * Methods for adding seats
     * @param flight    FlightEntity
     * @param seats     SeatEntity
     * @return  boolean
     */
    public boolean addSeats(FlightEntity flight, Set<SeatEntity> seats);
    
    /**
     * Method that checks if a seat it's available with specific parameters
     * @param flight    FlightEntity
     * @param seat      SeatEntity
     * @return  boolean
     */
    public boolean checkAvaliabilty(FlightEntity flight, SeatEntity seat);
    
    /**
     * Method to check aviality 
     * @param flight    FlightEntity
     * @param seats     Seat
     * @return  boolean
     */
    public boolean checkAvaliability(FlightEntity flight, Set<SeatEntity> seats);
}
