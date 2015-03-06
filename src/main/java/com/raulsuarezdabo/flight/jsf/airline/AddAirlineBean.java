/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.jsf.airline;

import com.mycompany.flight.utils.Utils;
import com.raulsuarezdabo.flight.entity.AirlineEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import com.raulsuarezdabo.flight.service.AirlineService;
import com.raulsuarezdabo.flight.service.CountryService;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class AddAirlineBean {

    /**
     * Creates a new instance of AddAirlineBean
     */
    public AddAirlineBean() {
    }
    
    /**
     * airline name
     */
    private String name;
    
    /**
     * airline code
     */
    private String code;
    
    /**
     * country of the airline
     */
    private String country;

    
    
    /**
     * Country service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{countryService}")
    private CountryService countryService;
    
    /**
     * Airline service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{airlineService}")
    private AirlineService airlineService;
    
    /**
     * List of countries
     */
    private List<CountryEntity> countries;
    
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
        this.name = name.toUpperCase();
    }

    /**
     * Getter code
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter code
     * @param code  String 
     */
    public void setCode(String code) {
        if (this.airlineService.getByCode(code) == null) {
            this.code = code;
        }
        else {
            // Bring the error message using the Faces Context
            String errorMessage = FacesContext.getCurrentInstance().getApplication().
                getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("codeExist");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("airlineForm:airlineCode", message);
            this.code = null;
        }
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
     * Getter airlineService
     * @return  AirlineService
     */
    public AirlineService getAirlineService() {
        return airlineService;
    }

    /**
     * Setter airlineService
     * @param airlineService    AirlineService
     */
    public void setAirlineService(AirlineService airlineService) {
        this.airlineService = airlineService;
    }
    
    public String addAirlineAction() {
        try {
            AirlineEntity airline = 
                this.airlineService.addAirline(
                    this.name, 
                    this.code, 
                    Utils.getCountryFromList(this.country, this.countries)
            );
            if (airline != null) {
                //Success update
            }
            else {
                //Error creating
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/airline/index?faces-redirect=true";
    }
}
