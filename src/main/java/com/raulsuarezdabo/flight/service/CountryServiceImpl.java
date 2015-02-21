/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.service;

import com.mycompany.flight.dao.CountryDAO;
import com.mycompany.flight.entity.CountryEntity;
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
    
}
