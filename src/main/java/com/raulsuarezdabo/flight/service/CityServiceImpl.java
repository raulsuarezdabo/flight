
package com.raulsuarezdabo.flight.service;

import com.mycompany.flight.dao.CityDAO;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class that implements the defined methods from the interface
 * @author raulsuarez
 */
@Service
public class CityServiceImpl implements CityService {
    
    @Autowired
    CityDAO cityDAO;

    /**
     * Method that returns the City from an specific code
     * @param id    int with the code of the city
     * @return  CityEntity  from the city's code
     */
    @Override
    @Transactional
    public CityEntity getById(int id) {
        try {
            CityEntity city = this.cityDAO.findById(id);
            return city;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method for obtaining the cities list
     * @return List with the CityEntity
     */
    @Override
    @Transactional
    public List<CityEntity> getAll() {
        return this.cityDAO.findAll();
    }

    /**
     * 
     * @param country
     * @return 
     */
    @Override
    @Transactional
    public List<CityEntity> getCitiesByCountry(CountryEntity country) {
        return this.cityDAO.findByCountry(country);
    }
    
    /**
     * Method to get a country list names
     * @return List of countries names
     */
    @Override
    @Transactional
    public List<String> getAllNames() {
        List <CityEntity> cities = this.cityDAO.findAll();
        List <String> names = new ArrayList();
        for (CityEntity city : cities) {
            names.add(city.getName());
        }
        return names;
    }
    
}
