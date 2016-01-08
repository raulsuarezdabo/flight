/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.dao.AirplaneDAOImpl;
import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for accessing to airplane information
 * @author raulsuarez
 */
@Service
@Transactional
public class AirplaneServiceImpl implements AirplaneService {
    
    @Autowired
    private AirplaneDAOImpl airplaneDAO;

    /**
     * Method to add new entry on the db as airplane
     * @param model String
     * @param maker String
     * @param year  Integer
     * @param numTotalSeats Integer
     * @param numOfferSeats Integer
     * @param numTouristSeats   Integer
     * @param numBusinessSeats  Integer
     * @return  AirplaneEntity
     */
    @Override
    @Transactional
    public AirplaneEntity addAirplane(String model, String maker, Integer year, Integer numTotalSeats, Integer numOfferSeats, Integer numTouristSeats, Integer numBusinessSeats) {
        try {
        AirplaneEntity airplane = new AirplaneEntity();
        
        airplane.setModel(model);
        airplane.setMaker(maker);
        airplane.setYear(year);
        airplane.setNumSeatsTotal(numTotalSeats);
        airplane.setNumSeatsOffer(numOfferSeats);
        airplane.setNumSeatsTourist(numTouristSeats);
        airplane.setNumSeatsBusiness(numBusinessSeats);
        this.airplaneDAO.addAirplane(airplane);
        return airplane;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Update airplane information
     * @param id    Integer
     * @param airplane  AirplaneEntity
     * @param update    boolean to persist or not
     * @return  AirplaneEntity
     */
    @Override
    @Transactional
    public AirplaneEntity updateAirplane(int id, AirplaneEntity airplane, boolean update) {
        try {
            AirplaneEntity airplaneToUpdate = this.airplaneDAO.findById(id);
            
            airplaneToUpdate.setMaker(airplane.getMaker());
            airplaneToUpdate.setModel(airplane.getModel());
            airplaneToUpdate.setNumSeatsBusiness(airplane.getNumSeatsBusiness());
            airplaneToUpdate.setNumSeatsOffer(airplane.getNumSeatsOffer());
            airplaneToUpdate.setNumSeatsTourist(airplane.getNumSeatsTourist());
            airplaneToUpdate.setNumSeatsTotal(airplane.getNumSeatsTotal());
            airplaneToUpdate.setYear(airplane.getYear());
            
            if (this.airplaneDAO.updateAirplane(airplaneToUpdate) == false) {
                throw new Exception("Error updating the airline");
            }
            return airplaneToUpdate;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method to delete an airplane
     * @param id    Integer
     * @return  boolean success/fails
     */
    @Override
    @Transactional
    public boolean deleteAirplane(int id) {
        try {
            AirplaneEntity airplane = this.airplaneDAO.findById(id);
            if (airplane == null) {
                throw new Exception("Not found airline");
            }
            return this.airplaneDAO.deleteAirplane(airplane);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Method for obtaining the list of airplanes models
     * @return  List    Of airplanes
     */
    @Override
    public List<AirplaneEntity> getAll() {
        try {
            List<AirplaneEntity> airplanes = this.airplaneDAO.findAll();
            return airplanes;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method to obtain an airplane by id
     * @param id    Integer
     * @return  AirplaneEntity
     */
    @Override
    public AirplaneEntity getById(int id) {
        return this.airplaneDAO.findById(id);
    }
    
}
