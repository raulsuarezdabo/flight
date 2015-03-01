/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
