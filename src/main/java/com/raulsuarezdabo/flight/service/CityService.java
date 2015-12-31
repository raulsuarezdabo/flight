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

import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.List;

/**
 * Interface for defining the methods to access to the city's repository
 * @author raulsuarez
 */
public interface CityService {
    
    /**
     * Method that defines the access to recover CityEntity by id
     * @param id
     * @return 
     */
    CityEntity getById(int id);
    
    /**
     * Method that defines to obtain all list of cities available
     * @return 
     */
    List<CityEntity> getAll();
    
    /**
     * Method to obtain the list of available cities from one country
     * @param country   CountryEntity
     * @return List     Of CityEntity from one country
     */
    List<CityEntity> getCitiesByCountry(CountryEntity country);
    
    /**
     * Method that obtains the completly list of countries available, only names
     * @return List with the String names
     */
    List<String> getAllNames();
}
