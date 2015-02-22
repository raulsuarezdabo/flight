/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
}
