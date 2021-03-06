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

import com.raulsuarezdabo.flight.dao.CountryDAO;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class that defines the implementacion from the CountryService
 * @author raulsuarez
 */
@Service
public class CountryServiceImpl implements CountryService {

    /**
     * Repository to access the country
     */
    @Autowired
    CountryDAO countryDAO;
    
    /**
     * Method for obtaining the list of countries
     * @return List Country's list
     */
    @Override
    @Transactional
    public List<CountryEntity> getAll() {
        return this.countryDAO.findAll();
    }

    /**
     * Method to get a country by code
     * @param code  String with the code of the country
     * @return CountryEntity    Entity with the specific code
     */
    @Override
    @Transactional
    public CountryEntity getByCode(String code) {
        return this.countryDAO.findById(code);
    }

    /**
     * Method to get a country list names
     * @return List of countries names
     */
    @Override
    @Transactional
    public List<String> getAllNames() {
        List <CountryEntity> countries = this.countryDAO.findAll();
        List <String> names = new ArrayList();
        for (CountryEntity countrie : countries) {
            names.add(countrie.getName());
        }
        return names;
    }
    
}
