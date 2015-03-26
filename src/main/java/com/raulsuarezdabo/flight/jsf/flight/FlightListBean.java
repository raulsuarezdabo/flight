
package com.raulsuarezdabo.flight.jsf.flight;

import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.service.AirportService;
import com.raulsuarezdabo.flight.service.FlightService;
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
public class FlightListBean {
    
    /**
     * Airport service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{flightService}")
    private FlightService flightService;
    
    @Autowired
    @ManagedProperty(value="#{airportService}")
    private AirportService airportService;
    
    /**
     * Airport list
     */
    private List<FlightEntity> flights;

    /**
     * Creates a new instance of FlightListBean
     */
    public FlightListBean() {
    }

    /**
     * Getter flightService
     * @return  FlightService
     */
    public FlightService getFlightService() {
        return flightService;
    }

    /**
     * Setter flightService
     * @param flightService FlightService 
     */
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
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
     * Getter flights
     * @return  List of flights
     */
    public List<FlightEntity> getFlights() {
        return flights;
    }

    /**
     * Setter flights
     * @param flights   List of flights 
     */
    public void setFlights(List<FlightEntity> flights) {
        this.flights = flights;
    }
    
    @PostConstruct
    public void init() {
        this.flights = this.flightService.getAll();
    }
}
