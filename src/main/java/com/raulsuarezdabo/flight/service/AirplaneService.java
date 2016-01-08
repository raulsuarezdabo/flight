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

import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import java.util.List;

/**
 * Interface for defining the methods to access for the data
 * @author raulsuarez
 */
public interface AirplaneService {
    /**
     * Method for adding new airplane
     * @param model
     * @param maker
     * @param year
     * @param numTotalSeats
     * @param numOfferSeats
     * @param numTouristSeats
     * @param numBusinessSeats
     * @return  AirplaneEntity
     */
    public AirplaneEntity addAirplane(
        String model, 
        String maker, 
        Integer year, 
        Integer numTotalSeats, 
        Integer numOfferSeats, 
        Integer numTouristSeats, 
        Integer numBusinessSeats
    );
    
    /**
     * Method for update the airplane information
     * @param id    int
     * @param airplane   AirplaneEntity   Information to update
     * @param update    boolean indicates if persist or not
     * @return AirplaneEntity    airplane updated or null if some problem
     */
    public AirplaneEntity updateAirplane(int id, AirplaneEntity airplane, boolean update);
    
    /**
     * Method to delete an airplane
     * @param id    int identifier of the aiport
     * @return boolean  if it's success or not
     */
    public boolean deleteAirplane(int id);
    
    /**
     * Get all airplanes available
     * @return List all airplanes listed
     */
    public List<AirplaneEntity> getAll();
    
    /**
     * Get an airplane by id
     * @param id    int
     * @return AirplaneEntity
     */
    public AirplaneEntity getById(int id);
    
}
