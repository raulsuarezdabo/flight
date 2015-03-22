/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.dao.FlightDAO;
import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import java.util.Date;
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
     * @param code  String  code
     * @param airportFrom   AirportEntity   airport it comes
     * @param airportTo AirportEntity   airport it goes
     * @param Start Date    when takes off
     * @param ends  Date    arrives
     * @param airplane  AirplaneEntity  airplane that is going to use
     * @return  FlightEntity    New flight
     */
    @Override
    @Transactional
    public FlightEntity addFlight(String code, AirportEntity airportFrom, AirportEntity airportTo, Date Start, Date ends, AirplaneEntity airplane) {
        try {
        FlightEntity flight = new FlightEntity();
        
        flight.setCode(code);
        flight.setAirportFrom(airportFrom);
        flight.setAirportTo(airportTo);
        flight.setStart(Start);
        flight.setEnds(ends);
        flight.setAirplane(airplane);
        
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
            if (flight.getCode() != null) {
                flightToUpdate.setCode(flight.getCode());
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
            if (flight.getEnds() != null) {
                flightToUpdate.setEnds(flight.getEnds());
            }
            if (flight.getAirplane() != null) {
                flightToUpdate.setAirplane(flight.getAirplane());
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
    
}
