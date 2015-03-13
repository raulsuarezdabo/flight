package com.mycompany.flight.dao;

import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.List;

/**
 *
 * @author raulsuarez
 */
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
