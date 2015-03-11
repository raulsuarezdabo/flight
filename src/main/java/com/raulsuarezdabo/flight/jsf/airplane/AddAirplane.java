
package com.raulsuarezdabo.flight.jsf.airplane;

import com.raulsuarezdabo.flight.service.AirplaneService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Bean for managing new airplane
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class AddAirplane {
    
    /**
     * Airline service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{airplaneService}")
    private AirplaneService airplaneService;

    /**
     * Creates a new instance of AddAirplane
     */
    public AddAirplane() {
    }
    
    /**
     * Model of the airplane
     */
    private String model;
    
    /**
     * maker of the airplane
     */
    private String maker;
    
    /**
     * year's airplane
     */
    private Integer year;
    
    /**
     * total seats
     */
    private Integer numSeatsTotal;
    
    /**
     * number offer seats
     */
    private Integer numSeatsOffer;
    
    /**
     * number tourist seats
     */
    private Integer numSeatsTourist;
    
    /**
     * number business seats
     */
    private Integer numSeatsBusiness;
    
    /**
     * Getter airplaneService
     * @return  AirplaneService
     */
    public AirplaneService getAirplaneService() {
        return airplaneService;
    }

    /**
     * Setter airplaneService
     * @param airplaneService   AirplaneService 
     */
    public void setAirplaneService(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    /**
     * Getter model
     * @return  String
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter model
     * @param model String 
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter maker
     * @return  String
     */
    public String getMaker() {
        return maker;
    }

    /**
     * Setter maker
     * @param maker String 
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    /**
     * Getter year
     * @return  Integer
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Setter year
     * @param year  Integer
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Getter num total seats
     * @return  Integer
     */
    public Integer getNumSeatsTotal() {
        return numSeatsTotal;
    }

    /**
     * Setter num total seats
     * @param numSeatsTotal     Integer
     */
    public void setNumSeatsTotal(Integer numSeatsTotal) {
        this.numSeatsTotal = numSeatsTotal;
    }

    /**
     * Getter num offer seats
     * @return  Integer
     */
    public Integer getNumSeatsOffer() {
        return numSeatsOffer;
    }

    /**
     * Setter number of offer seats
     * @param numSeatsOffer     Integer
     */
    public void setNumSeatsOffer(Integer numSeatsOffer) {
        this.numSeatsOffer = numSeatsOffer;
    }

    /**
     * Getter num seats tourist
     * @return Integer
     */
    public Integer getNumSeatsTourist() {
        return numSeatsTourist;
    }

    /**
     * Setter num seats tourist
     * @param numSeatsTourist   Integer
     */
    public void setNumSeatsTourist(Integer numSeatsTourist) {
        this.numSeatsTourist = numSeatsTourist;
    }

    /**
     * Getter number seats business
     * @return Integer
     */
    public Integer getNumSeatsBusiness() {
        return numSeatsBusiness;
    }

    /**
     * Setter number seats business
     * @param numSeatsBusiness Integer
     */
    public void setNumSeatsBusiness(Integer numSeatsBusiness) {
        this.numSeatsBusiness = numSeatsBusiness;
    }
    
    
    
}
