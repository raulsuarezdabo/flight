/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.utils;

import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import com.raulsuarezdabo.flight.entity.RoleEntity;
import static java.lang.Math.abs;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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
            if (currentCity.getId() == cityId) {
                found = true;
            }
        }
        return currentCity;
    }

    /**
     * Method for generating the token
     * @return String with the token
     */
    public static String generateToken() {
        try {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        return String.valueOf(abs(secureRandom.nextLong()));
        } catch(NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Method for obtaining the hole url string
     * 
     * @param target
     * @param get   Map String map of the get parameters
     * @return  String  url
     */
    public static String getUrl(String target, HashMap get) {
        HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = "http://flight-now.project.suarez.com.es/";
        if (target != null || get != null ) {
            if (target != null) {
                url = url.concat(target);
            }
            
            boolean first = true;
            
            if (get != null && get.isEmpty() == false) {
                Set<String> key = get.keySet();
                Iterator iterator = key.iterator();
                while (iterator.hasNext() == true) {
                    String next = (String) iterator.next();
                    String value = (String) get.get(next);
                    if (first == true) {
                        url = url.concat("?" + next + "=" + value);
                        first = false;
                    }
                    else {
                        url = url.concat("&" + next + "=" + value);
                    }
                }
            }
        }
        return url;
    }
    
    /**
     * Method for getting the list of roles from the list of ids
     * @param role
     * @param roles
     * @return  List of roles
     */
    public static List<RoleEntity> getRolesFromList(List<Integer> role, List<RoleEntity> roles) {
        Iterator iteratorI = role.iterator();
        ArrayList result = new ArrayList();
        
        while (iteratorI.hasNext() == true) {
            String currentI = (String) iteratorI.next();
            for(RoleEntity thisRole: roles) {
                if (thisRole.getId() == Integer.parseInt(currentI)) {
                    result.add(thisRole);
                }
            }
        }
        return result;
    }
    
    /**
     * List integer generator
     * @param from
     * @param to
     * @param pass
     * @return  List of integers
     */
    public static List<Integer> getIntegerList(int from, int to, int pass) {
        List<Integer> list = new ArrayList();
        for (int i = from;i <= to; i=i+pass ) {
            list.add(new Integer(i));
        }
        return list;
    }
    
}
