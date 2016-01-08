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

import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import java.util.List;

/**
 *
 * @author raulsuarez
 */
public interface SeatService {
    
    /**
     * Get all seats available
     * @return List all seat listed
     */
    public List<SeatEntity> getAll();
    
    /**
     * Get an seat by id
     * @param id    int
     * @return SeatEntity
     */
    public SeatEntity getById(int id);
    
     /**
     * This method it's to count the number of seats used on a flight
     * @param flight
     * @param type
     * @return 
     */
    public int numberSeatsUsed(FlightEntity flight, int type);
}
