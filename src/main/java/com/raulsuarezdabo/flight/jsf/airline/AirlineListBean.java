package com.raulsuarezdabo.flight.jsf.airline;

import com.raulsuarezdabo.flight.entity.AirlineEntity;
import com.raulsuarezdabo.flight.service.AirlineService;
import com.raulsuarezdabo.flight.service.CountryService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JSF bean for managing the list of airports available
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class AirlineListBean implements Serializable {

    /**
     * Airline service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{airlineService}")
    private AirlineService airlineService;
    
    /**
     * Country service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{countryService}")
    private CountryService countryService;
    
    /**
     * List of countries
     */
    private List<String> countries;
    
    /**
     * Airline list
     */
    private List<AirlineEntity> airlines;
    
    /**
     * Airline filtered
     */
    private List<AirlineEntity> filteredAirline;
    /**
     * Creates a new instance of AirlineListBean
     */
    public AirlineListBean() {
    }
    
    /**
     * Getter airportService
     * @return  AirlineService
     */
    public AirlineService getAirlineService() {
        return airlineService;
    }

    /**
     * Setter airportService
     * @param airportService    AirlineService
     */
    public void setAirlineService(AirlineService airportService) {
        this.airlineService = airportService;
    }

    /**
     * Getter of the airportList
     * @return List
     */
    public List<AirlineEntity> getAirlines() {
        return airlines;
    }

    /**
     * Setter of the airport list
     * @param airlines
     */
    public void setAirlines(List<AirlineEntity> airlines) {
        this.airlines = airlines;
    }

    /**
     * Getter of the filtered list of airports
     * @return 
     */
    public List<AirlineEntity> getFilteredAirline() {
        return filteredAirline;
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
    
    @PostConstruct
    public void init() {
        this.airlines = this.airlineService.getAll();
    }
    
}
