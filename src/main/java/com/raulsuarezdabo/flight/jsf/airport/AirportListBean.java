package com.raulsuarezdabo.flight.jsf.airport;

import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.service.AirportService;
import com.raulsuarezdabo.flight.service.CountryService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JSF bean for managing the list of airports available
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class AirportListBean implements Serializable {

    /**
     * Airport service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{airportService}")
    private AirportService airportService;
    
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
     * Airport list
     */
    private List<AirportEntity> airports;
    
    /**
     * Airport filtered
     */
    private List<AirportEntity> filteredAirport;
    /**
     * Creates a new instance of AirportListBean
     */
    public AirportListBean() {
    }
    
    /**
     * Getter airportService
     * @return  AirportService
     */
    public AirportService getAirportService() {
        return airportService;
    }

    /**
     * Setter airportService
     * @param airportService    AirportService
     */
    public void setAirportService(AirportService airportService) {
        this.airportService = airportService;
    }

    /**
     * Getter of the airportList
     * @return List
     */
    public List<AirportEntity> getAirports() {
        return airports;
    }

    /**
     * Setter of the airport list
     * @param airports List
     */
    public void setAirports(List<AirportEntity> airports) {
        this.airports = airports;
    }

    /**
     * Getter of the filtered list of airports
     * @return 
     */
    public List<AirportEntity> getFilteredAirport() {
        return filteredAirport;
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
        this.airports = this.airportService.getAll();
    }
    
}
