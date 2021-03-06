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

import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import java.util.List;

/**
 * Interface for defining the methods to access to airplane information
 * @author raulsuarez
 */
public interface AirplaneDAO {
   
    /**
     * Get all airplane
     * @return List
     */
    public List<AirplaneEntity> findAll();
    
    /**
     * Find airplane by id
     * @param id    int
     * @return  AirplaneEntity
     */
    public AirplaneEntity findById(int id);
    
    /**
     * Add new airplane
     * @param airplane
     */
    public void addAirplane(AirplaneEntity airplane);
    
    /**
     * Update an airplane
     * @param airplane
     * @return AirplaneEntity    returns the entity or null if error
     */
    public boolean updateAirplane(AirplaneEntity airplane);
    
    /**
     * Delete an airplane
     * @param airplane
     * @return boolean  with success or not
     */
    public boolean deleteAirplane(AirplaneEntity airplane);
}
