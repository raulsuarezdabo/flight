/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Class that defines the implementacion from the FlightService
 * @author raulsuarez
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {

    /**
     * Method for adding flights
     * @param code  String  code
     * @param status    int status of the flight
     * @param airportFrom   AirportEntity   airport it comes
     * @param airportTo AirportEntity   airport it goes
     * @param Start Date    when takes off
     * @param ends  Date    arrives
     * @return  FlightEntity    New flight
     */
    @Override
    public FlightEntity addFlight(String code, int status, AirportEntity airportFrom, AirportEntity airportTo, Date Start, Date ends) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Method for updating the flight
     * @param id    int reference of the flight
     * @param flight    FlightEntity    flight to update
     * @param update    boolean to persist or not
     * @return  FlightEntity    updated information
     */
    @Override
    public FlightEntity updateFlight(int id, FlightEntity flight, boolean update) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Method for deleting the flight
     * @param id    int id of the flight
     * @return  boolean success/fails to delete
     */
    @Override
    public boolean deleteFlight(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Method for getting all flights
     * @return  List    List of all FlightEntity
     */
    @Override
    public List<FlightEntity> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter by code
     * @param code  String  reference of the code
     * @return  FlightEntity    the referenced flight
     */
    @Override
    public FlightEntity getByCode(String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Getter by id
     * @param id    int reference primary key of the flight
     * @return  FlightEntity
     */
    @Override
    public FlightEntity getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
