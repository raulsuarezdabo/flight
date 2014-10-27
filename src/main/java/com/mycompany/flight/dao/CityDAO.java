package com.mycompany.flight.dao;

import com.mycompany.flight.entity.CityEntity;
import com.mycompany.flight.entity.CountryEntity;
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
