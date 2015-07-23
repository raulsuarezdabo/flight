/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.dao.FlightDAO;
import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class that defines the implementacion from the FlightService
 * @author raulsuarez
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    
    @Autowired
    private FlightDAO flightDAO;

    /**
     * Method for adding flights
     * @param airportFrom   AirportEntity   airport it comes
     * @param airportTo AirportEntity   airport it goes
     * @param Start Date    when takes off
     * @param time  int    time spending on it
     * @param airplane  AirplaneEntity  airplane that is going to use
     * @return  FlightEntity    New flight
     */
    @Override
    @Transactional
    public FlightEntity addFlight(AirportEntity airportFrom, AirportEntity airportTo, Date Start, Date time, AirplaneEntity airplane) {
        try {
        FlightEntity flight = new FlightEntity();
        
        flight.setAirportFrom(airportFrom);
        flight.setAirportTo(airportTo);
        flight.setStart(Start);
        flight.setTime(time);
        flight.setAirplane(airplane);
        flight.setStatus(FlightEntity.STATUSAVAILABLE);
        
        this.flightDAO.addFlight(flight);
        return flight;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method for updating the flight
     * @param id    int reference of the flight
     * @param flight    FlightEntity    flight to update
     * @param update    boolean to persist or not
     * @return  FlightEntity    updated information
     */
    @Override
    @Transactional
    public FlightEntity updateFlight(int id, FlightEntity flight, boolean update) {
        try {
            FlightEntity flightToUpdate = this.flightDAO.findById(id);
            if (flight == null) {
                throw new Exception("Not found flight");
            }
            if (flight.getAirportFrom() != null) {
                flightToUpdate.setAirportFrom(flight.getAirportFrom());
            }
            if (flight.getAirportTo() != null) {
                flightToUpdate.setAirportTo(flight.getAirportTo());
            }
            if (flight.getStart() != null) {
                flightToUpdate.setStart(flight.getStart());
            }
            if (flight.getTime()!= null) {
                flightToUpdate.setTime(flight.getTime());
            }
            if (flight.getAirplane() != null) {
                flightToUpdate.setAirplane(flight.getAirplane());
            }
            
            if (flight.getStatus() != FlightEntity.STATUSNONE) {
                flightToUpdate.setStatus(flight.getStatus());
            }
            
            if (this.flightDAO.updateFlight(flightToUpdate) == false) {
                throw new Exception("Error updating the flight");
            }
            return flightToUpdate;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method for deleting the flight
     * @param id    int id of the flight
     * @return  boolean success/fails to delete
     */
    @Override
    @Transactional
    public boolean deleteFlight(int id) {
        try {
            FlightEntity flight = this.flightDAO.findById(id);
            if (flight == null) {
                throw new Exception("Not found flight");
            }
            return this.flightDAO.deleteFlight(flight);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Method for getting all flights
     * @return  List    List of all FlightEntity
     */
    @Override
    public List<FlightEntity> getAll() {
        try {
            List<FlightEntity> flights = this.flightDAO.findAll();
            return flights;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Getter by code
     * @param code  String  reference of the code
     * @return  FlightEntity    the referenced flight
     */
    @Override
    public FlightEntity getByCode(String code) {
        return this.flightDAO.findByCode(code);
    }

    /**
     * Getter by id
     * @param id    int reference primary key of the flight
     * @return  FlightEntity
     */
    @Override
    public FlightEntity getById(int id) {
        return this.flightDAO.findById(id);
    }

    /**
     * Method to search using City comes and goes, and Date criteria
     * @param from  CityEntity
     * @param to    CityEntiy
     * @param when  Date
     * @param numPassengers int
     * @return  List    of flights
     */
    @Override
    public List<FlightEntity> searchFlights(CityEntity from, CityEntity to, Date when, int numPassengers) {
        try {
            List <FlightEntity> flights = this.flightDAO.findFlights(from, to, when);
            List <FlightEntity> results = new ArrayList<>();
            
            for (FlightEntity flight : flights) {
                if (numPassengers <= flight.getSeats().size()) {
                    results.add(flight);
                }
            }
            return results;
        } catch(Exception e) {
            return new ArrayList();
        }
    }
    
}
