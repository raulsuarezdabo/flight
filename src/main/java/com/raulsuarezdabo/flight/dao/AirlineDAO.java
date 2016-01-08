/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.AirlineEntity;
import java.util.List;

/**
 * Interface for defining the methods to access to airport information
 * @author raulsuarez
 */
public interface AirlineDAO {
   
    /**
     * Get all airports
     * @return List
     */
    public List<AirlineEntity> findAll();
    
    /**
     * Find airport by code
     * @param code  String
     * @return  AirlineEntity   airport
     */
    public AirlineEntity findByCode(String code);
    
    /**
     * Find airport by id
     * @param id    int
     * @return  AirlineEntity
     */
    public AirlineEntity findById(int id);
    
    /**
     * Add new airport
     * @param airline
     */
    public void addAirline(AirlineEntity airline);
    
    /**
     * Update an airport
     * @param airline
     * @return AirlineEntity    returns the entity or null if error
     */
    public boolean updateAirline(AirlineEntity airline);
    
    /**
     * Delete an airport
     * @param airline
     * @return boolean  with success or not
     */
    public boolean deleteAirline(AirlineEntity airline);
}
