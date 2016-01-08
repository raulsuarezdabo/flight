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

import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.List;

/**
 * Interface for defining the methods to implments that solve the necessities
 * @author raulsuarez
 */
public interface CountryService {
    
    /**
     * Method that obtains the completly list of Countries avaible
     * @return List with the CountryEntity list
     */
    List<CountryEntity> getAll();
    
    /**
     * Method that obtains the completly list of countries available, only names
     * @return List with the String names
     */
    List<String> getAllNames();
    
    /**
     * Method for obtaining the Country by code
     * @param code  String with the corresponding country code 
     * @return CountryEntity    
     */
    CountryEntity getByCode(String code);
}
