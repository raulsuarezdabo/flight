/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.raulsuarezdabo.flight.jsf.user;

import com.mycompany.flight.service.UserService;
import com.raulsuarezdabo.flight.entity.UserEntity;
import com.raulsuarezdabo.flight.service.CityService;
import com.raulsuarezdabo.flight.service.CountryService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class UserListBean {
    
    /**
     * User service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{userService}")
    private UserService userService;

    /**
     * Service of countries
     */
    @Autowired
    @ManagedProperty(value="#{countryService}")
    private CountryService countryService;
    
    /**
     * City service
     */
    @Autowired
    @ManagedProperty(value="#{cityService}")
    private CityService cityService;
    
    /**
     * List of countries
     */
    private List<String> countries;
    
    /**
     * List of countries
     */
    private List<String> cities;
    
    /**
     * Airport list
     */
    private List<UserEntity> users;
    
    /**
     * Airport filtered
     */
    private List<UserEntity> filteredUser;

    /**
     * Creates a new instance of UserListBean
     */
    public UserListBean() {
    }
    
    /**
     * Getter list of countries
     *
     * @return
     */
    public List<String> getCountries() {
        if (this.countries == null) {
            this.countries = this.countryService.getAllNames();
        }
        return this.countries;
    }

    /**
     * Setter countries
     *
     * @param countries
     */
    public void setCountries(List<String> countries) {
        this.countries = countries;
    }
    
    /**
     * Getter countryService
     * @return 
     */
    public CountryService getCountryService() {
        return countryService;
    }

    /**
     * Setter of the countryService
     * @param countryService 
     */
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
    
    /**
     * userSErvice Getter
     *
     * @return
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * userService Setter
     *
     * @param userService
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Getter city service
     * @return 
     */
    public CityService getCityService() {
        return cityService;
    }

    /**
     * Setter of city service
     * @param cityService 
     */
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Getter cities
     * @return List of cities
     */
    public List<String> getCities() {
        if (this.cities == null) {
            this.cities = this.cityService.getAllNames();
        }
        return cities;
    }

    /**
     * Setter cities
     * @param cities 
     */
    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    /**
     * Getter filteredUser 
     * @return List of users
     */
    public List<UserEntity> getFilteredUser() {
        return filteredUser;
    }

    /**
     * Setter filteredUser
     * @param filteredUser 
     */
    public void setFilteredUser(List<UserEntity> filteredUser) {
        this.filteredUser = filteredUser;
    }

    /**
     * Getter users
     * @return List of users
     */
    public List<UserEntity> getUsers() {
        return users;
    }

    /**
     * Setter user list
     * @param users     List of users
     */
    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
    
    @PostConstruct
    public void init() {
        this.users = this.userService.getAll();
    }
    
}
