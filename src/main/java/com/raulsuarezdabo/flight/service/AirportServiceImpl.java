package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.dao.AirportDAO;
import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service that's implements all method defined on the interface
 * @author raulsuarez
 */
@Service
@Transactional
public class AirportServiceImpl implements AirportService {
    
    @Autowired
    private AirportDAO airportDAO;

    /**
     * Method for adding an aiport
     * @return 
     */
    @Override
    @Transactional
    public AirportEntity addAirport(String name, String code, CountryEntity country, CityEntity city) {
        try {
        AirportEntity airport = new AirportEntity();
        
        airport.setName(name);
        airport.setCode(code);
        airport.setCountry(country);
        airport.setCity(city);
        this.airportDAO.addAirport(airport);
        return airport;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method for update the airport information
     * @param id    int
     * @param airport   AirportEntity   Information to update
     * @param update    boolean indicates if persist or not
     * @return AirportEntity    airport updated or null if some problem
     */
    @Override
    @Transactional
    public AirportEntity updateAirport(int id, AirportEntity airport, boolean update) {
        try {
            AirportEntity airportToUpdate = this.airportDAO.findById(id);
            airportToUpdate.setName(airport.getName());
            airportToUpdate.setCode(airport.getCode());
            airportToUpdate.setCountry(airport.getCountry());
            airportToUpdate.setCity(airport.getCity());
            if (this.airportDAO.updateAirport(airport) == false) {
                throw new Exception("Error updating the airport");
            }
            return airportToUpdate;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    /**
     * Method to delete an airport
     * @param id    int identifier of the aiport
     * @return boolean  if it's success or not
     */
    @Override
    @Transactional
    public boolean deleteAirport(int id) {
        try {
            AirportEntity airport = this.airportDAO.findById(id);
            if (airport == null) {
                throw new Exception("Not found airport");
            }
            return this.airportDAO.deleteAirport(airport);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        
    }

    /**
     * Get all airports available
     * @return List all airports listed
     */
    @Override
    public List<AirportEntity> getAll() {
        return this.airportDAO.findAll();
    }

    /**
     * Get an airport by code
     * @param code  String  code
     * @return  AirportEntity   airport
     */
    @Override
    public AirportEntity getByCode(String code) {
        return this.airportDAO.findByCode(code);
    }
    
    /**
     * Get an airport by Id
     * @param id
     * @return  AirportEntity   airport
     */
    @Override
    public AirportEntity getById(int id) {
        return this.airportDAO.findById(id);
    }
    
}
