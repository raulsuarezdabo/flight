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

import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import com.raulsuarezdabo.flight.service.UserService;
import com.raulsuarezdabo.flight.utils.Utils;
import com.raulsuarezdabo.flight.jsf.message.Message;
import com.raulsuarezdabo.flight.service.CityService;
import com.raulsuarezdabo.flight.service.CountryService;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author raulsuarez
 */
@ManagedBean(eager=true)
@ViewScoped
public class EditUserBean {

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
    private List<CountryEntity> countries;

    /**
     * List of cities
     */
    private List<CityEntity> cities;

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
            this.countries = this.countryService.getAll();
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
            this.cities = this.cityService.getCitiesByCountry(Utils.getCountryFromList(this.country, this.countries));
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
     * Getter country service
     * @return 
     */
    public CountryService getCountryService() {
        return countryService;
    }

    /**
     * Setter country service
     * @param countryService 
     */
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
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
    
    @PostConstruct
    public void init() {
        UserEntity user = this.userService.getLoggedUser();
        if ( (user != null) && (user instanceof UserEntity == true) ) {
            UserEntity currentUser = this.userService.getByEmail(user.getEmail());
            this.email = currentUser.getEmail();
            this.name = currentUser.getName();
            this.surname = currentUser.getSurname();
            this.birthday = currentUser.getBirthDay();
            this.nif = currentUser.getNif();
            this.phone = currentUser.getPhone();
            this.address = currentUser.getAddress();
            this.country = currentUser.getCountry().getCode();
            this.city = currentUser.getCity().getId();
        }
    }
    
    /**
     * Method for updating the information of the user
     * @return boolean  update jsf page
     */
    public String updateAction () {
        UserEntity editUser = new UserEntity();
        editUser.setName(this.getName());
        editUser.setSurname(this.getSurname());
        editUser.setBirthDay(new java.sql.Date(this.getBirthday().getTime()));
        editUser.setNif(this.getNif());
        editUser.setPhone(this.getPhone());
        editUser.setAddress(this.getAddress());
        editUser.setCountry(this.countryService.getByCode(this.getCountry()));
        editUser.setCity(this.cityService.getById(this.getCity()));
        UserEntity userUpdated = this.userService.updateUser(this.email, editUser, true);
        if (userUpdated == null ) {
            //Error on persisting the user
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("updateDangerUserMessage")
                );
        }
        else {
            //If not error is updated
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.SUCCESS, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("updateSuccessUserMessage")
                );
        }
        return "/profile/edit?faces-redirect=true";
    }
}
