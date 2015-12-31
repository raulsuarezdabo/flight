/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.mycompany.flight.dao;

import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.List;

/**
 *
 * @author raulsuarez
 */
public interface CityDAO {
    /**
     * Find by code on the country table
     *
     * @param id
     * @return CountryEntity with the param asociated or null
     */
    public CityEntity findById(int id);
    
    /**
     * Find a list of Cities from a Country
     * @param country
     * @return 
     */
    public List<CityEntity> findByCountry(CountryEntity country);
    
    /**
     * Get all from country table
     * @return List of countryEntity
     */
    public List<CityEntity> findAll();

}
