
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
     * Find flights by date of takes off
     * @param from  Date
     * @param to    Date
     * @return  List of Flights
     */
    public List<FlightEntity> findByDate(Date from, Date to);
    
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
    
    /**
     * Method for searching results of flights with the specific criteria
     * @param from  CityEntity
     * @param to    CityEntity
     * @param when  Date
     * @return  List flights available with this criteria
     */
    public List<FlightEntity> findFlights(CityEntity from, CityEntity to, Date when);
    
    /**
     * Method that sets a couple of seats
     * @param flight    FlightEntity
     * @param seats SeatEntity
     * @param book  BookEntity
     * @return  boolean
     */
    public boolean setSeatsToFlight(FlightEntity flight, Set<SeatEntity> seats, BookEntity book);
    
    /**
     * Method that sets a couple of seats
     * @param flight    FlightEntity
     * @param seat  SeatEntity
     * @param book  BookEntity
     * @return  boolean
     */
    public boolean setSeatToFlight(FlightEntity flight, SeatEntity seat, BookEntity book);
    
    /**
     * Method to find list of flights for tracking
     * @param days  int
     * @return  List
     */
    public List findCountFlightsByDate(int days);
    
    /**
     * 
     * @param flight    FlightEntity
     * @param type  int
     * @return  List of seats
     */
    public List<SeatEntity> getSeatsByFlightClass(FlightEntity flight, int type);
    
    /**
     * Find flights that takes off by date
     * @param date
     * @return  List
     */
    public List<FlightEntity> findByDate(Date date);
    
}
