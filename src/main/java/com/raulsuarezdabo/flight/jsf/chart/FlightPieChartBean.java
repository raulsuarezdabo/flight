/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.jsf.chart;

import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.ClassEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import com.raulsuarezdabo.flight.service.FlightService;
import com.raulsuarezdabo.flight.service.SeatService;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Bean for manage the generic information of the flight
 * @author raulsuarez
 */
@ManagedBean
public class FlightPieChartBean {
    
    private PieChartModel pieModel1;

    /**
     * Airport service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{flightService}")
    private FlightService flightService;
    
    /**
     * seat service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{seatService}")
    private SeatService seatService;

    /**
     * Creates a new instance of FlightChartBean
     */
    public FlightPieChartBean() {
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
     * Getter SeatService
     * @return  SeatService
     */
    public SeatService getSeatService() {
        return seatService;
    }

    /**
     * Setter seatService
     * @param seatService   SeatService 
     */
    public void setSeatService(SeatService seatService) {
        this.seatService = seatService;
    }
    
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    
    private void createPieModel1(FlightEntity flight) {
        
        AirplaneEntity airplane = flight.getAirplane();
        
        int touristUsed = this.seatService.numberSeatsUsed(flight, ClassEntity.TOURIST);
        int businesUsed = this.seatService.numberSeatsUsed(flight, ClassEntity.BUSINESS);
        int offerUsed = this.seatService.numberSeatsUsed(flight, ClassEntity.OFFER);
        
        pieModel1 = new PieChartModel();
         
        pieModel1.set(FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                FacesContext.getCurrentInstance(), "msg").getString("touristUsed"),
            touristUsed);
        pieModel1.set(FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                FacesContext.getCurrentInstance(), "msg").getString("touristFree"),
            airplane.getNumSeatsTourist() - touristUsed);
        pieModel1.set(FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                FacesContext.getCurrentInstance(), "msg").getString("businessUsed"),
            businesUsed);
        pieModel1.set(FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                FacesContext.getCurrentInstance(), "msg").getString("businessFree"), 
            airplane.getNumSeatsBusiness() - businesUsed);
         
        pieModel1.setTitle(FacesContext.getCurrentInstance().getApplication().getResourceBundle(
            FacesContext.getCurrentInstance(), "msg").getString("chartOcupancyFlight")
        );
        pieModel1.setLegendPosition("w");
        pieModel1.setShowDataLabels(true);
    }
    
    private void createPieModels(FlightEntity flight) {
        this.createPieModel1(flight);
    }
    
    @PostConstruct
    public void init() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer id = (Integer)parseInt(parameterMap.get("parameter"));
        FlightEntity flight = this.flightService.getById(id);
        if (flight != null && flight instanceof FlightEntity) {
            createPieModels(flight);
        }
    }
}
