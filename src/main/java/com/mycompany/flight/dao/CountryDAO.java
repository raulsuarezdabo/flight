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

import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.List;

public interface CountryDAO {
    /**
     * Find by code on the country table
     *
     * @param code
     * @return CountryEntity with the param asociated or null
     */
    public CountryEntity findById(String code);
    
    /**
     * Get all from country table
     * @return List of countryEntity
     */
    public List<CountryEntity> findAll();

}
