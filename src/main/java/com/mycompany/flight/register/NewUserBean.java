package com.mycompany.flight.register;

import com.mycompany.flight.dao.CityDAO;
import com.mycompany.flight.dao.CountryDAO;
import com.mycompany.flight.entity.CityEntity;
import com.mycompany.flight.entity.CountryEntity;
import com.mycompany.flight.entity.UserEntity;
import com.mycompany.flight.service.UserService;
import com.mycompany.flight.utils.Utils;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author raulsuarez
 */
public class NewUserBean implements Serializable {

    /**
     * Name of the user
     */
    private String name;
    /**
     * Surname of the user
     */
    private String surname;
    /**
     * email of the user
     */
    private String email;
    /**
     * birthday of the user
     */
    private Date birthday;

    /**
     * phone of the user
     */
    private String phone;

    /**
     * nif of the user
     */
    private String nif;

    /**
     * address of the user
     */
    private String address;

    /**
     * country of the user
     */
    private String country;

    /**
     * city of the user
     */
    private int city;

    /**
     * User service to use on the view
     */
    private UserService userService;

    /**
     * DAO of countries
     */
    private CountryDAO countryDAO;

    /**
     * List of countries
     */
    private List<CountryEntity> countries;

    /**
     * List of cities
     */
    private List<CityEntity> cities;

    /**
     * DAO of cities
     */
    private CityDAO cityDAO;

    /**
     * Getter of name
     *
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     *
     * @param name name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of surname
     *
     * @return String
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter of surname
     *
     * @param surname surname of the user
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter of the user
     *
     * @return email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of the email
     *
     * @param email email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter of the birthday
     *
     * @return
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Setter of the birthday
     *
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Getter of the phone
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter of the phone
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter of the nif
     *
     * @return
     */
    public String getNif() {
        return nif;
    }

    /**
     * Setter of the nif
     *
     * @param nif
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Getter of the address
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter of the address
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter country
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter country
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Getter list of countries
     *
     * @return
     */
    public List<CountryEntity> getCountries() {
        if (this.countries == null) {
            this.countries = this.countryDAO.findAll();
        }
        return this.countries;
    }

    /**
     * Setter countries
     *
     * @param countries
     */
    public void setCountries(List<CountryEntity> countries) {
        this.countries = countries;
    }

    /**
     * Getter Cities
     *
     * @return
     */
    public List<CityEntity> getCities() {
        if (this.country != null) {
            this.cities = this.cityDAO.findByCountry(Utils.getCountryFromList(this.country, this.countries));
        } else {
            this.cities = null;
        }
        return this.cities;
    }

    /**
     * Setter of cities
     *
     * @param cities
     */
    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
    }

    /**
     * Getter city
     *
     * @return CityEntity
     */
    public int getCity() {
        return city;
    }

    /**
     * Setter city
     *
     * @param city
     */
    public void setCity(int city) {
        this.city = city;
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
     * CountryDAO Getter
     *
     * @return
     */
    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    /**
     * Setter countryDAO
     *
     * @param countryDao
     */
    public void setCountryDAO(CountryDAO countryDao) {
        this.countryDAO = countryDao;
    }

    /**
     * Getter citydao
     *
     * @return
     */
    public CityDAO getCityDAO() {
        return cityDAO;
    }

    /**
     * Setter of cityDao
     *
     * @param cityDAO
     */
    public void setCityDAO(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    /**
     * Method that creates the new user
     *
     * @return
     */
    public Boolean singUpAction() {
        try {
            UserEntity user = this.userService.newUser(
                this.email, 
                this.name, 
                this.surname, 
                this.address, 
                this.nif, 
                this.phone, 
                this.birthday, 
                Utils.getCountryFromList(this.country, this.countries), 
                Utils.getCityFromList(this.city, this.cities)
            );
            if (user == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
