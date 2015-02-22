/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.utils;

import com.raulsuarezdabo.flight.entity.CityEntity;
import com.mycompany.flight.entity.CountryEntity;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author raulsuarez
 */
public class Utils {

    /**
     * Method to find in a list a specific countryEntity
     *
     * @param countryCode
     * @param countries
     * @return
     */
    public static CountryEntity getCountryFromList(String countryCode, List<CountryEntity> countries) {
        Iterator iterator = countries.iterator();
        boolean found = false;
        CountryEntity currentCountry = null;
        while (iterator.hasNext() == true && found == false) {
            currentCountry = (CountryEntity) iterator.next();
            if (currentCountry.getCode().equals(countryCode) == true) {
                found = true;
            }
        }
        return currentCountry;
    }

    /**
     * Method to find a list in a specific cityEntity
     *
     * @param cityId
     * @param cities
     * @return
     */
    public static CityEntity getCityFromList(int cityId, List<CityEntity> cities) {
        Iterator iteratorCities = cities.iterator();
        boolean found = false;
        CityEntity currentCity = null;
        while (iteratorCities.hasNext() == true && found == false) {
            currentCity = (CityEntity) iteratorCities.next();
            if (currentCity.getId().equals(cityId) == true) {
                found = true;
            }
        }
        return currentCity;
    }
}
