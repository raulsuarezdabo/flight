package com.raulsuarezdabo.flight.jsf.airport;

import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.service.AirportService;
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
     * Setter fo the filtered list of airports
     * @param filteredAirport 
     */
    public void setFilteredAirport(List<AirportEntity> filteredAirport) {
        this.filteredAirport = filteredAirport;
    }
    
    @PostConstruct
    public void init() {
        this.airports = this.airportService.getAll();
    }
    
}
