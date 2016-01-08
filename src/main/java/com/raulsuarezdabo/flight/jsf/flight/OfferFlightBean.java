/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.jsf.flight;

import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.service.FlightService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class OfferFlightBean {
    
    List<FlightEntity> flights;
    
    Date date;
    
    /**
     * Airport service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{flightService}")
    private FlightService flightService;
    
    /**
     * Getter flightService
     *
     * @return FlightService
     */
    public FlightService getFlightService() {
        return flightService;
    }

    /**
     * Setter flightService
     *
     * @param flightService FlightService
     */
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    /**
     * Getter flights
     * @return 
     */
    public List<FlightEntity> getFlights() {
        return flights;
    }

    /**
     * Setter flights
     * @param flights 
     */
    public void setFlights(List<FlightEntity> flights) {
        this.flights = flights;
    }

    /**
     * Creates a new instance of OfferFlightBean
     */
    public OfferFlightBean() {
    }
    
    public String linkOfferSearch(FlightEntity flight) {
        DateFormat df = new SimpleDateFormat("M/d/yyyy");
        return "/booking-process/results?faces-redirect=true"
            + "&from=" + flight.getAirportFrom().getCity().getId().toString()
            + "&to=" + flight.getAirportTo().getCity().getId().toString()
            + "&start=" + df.format(flight.getStart())
            + "&oneway=" + "true"
            + "&passengers=" + 1;
    }
    
    @PostConstruct
    public void init() {
        this.flights = this.flightService.getOffersOfDay();
    }
}
