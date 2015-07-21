package com.raulsuarezdabo.flight.pojo;

import com.raulsuarezdabo.flight.entity.CityEntity;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Pojo class to store parameters from search booking form
 * @author raulsuarez
 */
public class BookingSearchPojo implements Serializable {
    
    /**
     * comes from
     */
    private CityEntity flightFrom;

    /**
     * goes to
     */
    private CityEntity flightTo;

    /**
     * Date where goes
     */
    private Date flightStart;

    /**
     * Date where comes back
     */
    private Date flightFinish;

    /**
     * number of passengers
     */
    private int flightPassengers;
    
    /**
     * one way or not
     */
    private boolean flightOneWay;

    /**
     * Terms and conditions of the flight
     */
    private boolean flightTermsConditions;
    
    /**
     * Id for selected flight to go
     */
    private Integer selectedFlightGo;

    /**
     * Id for selected flight to come back
     */
    private Integer selectedFlightBack;

    /**
     * default constructor
     */
    public BookingSearchPojo() {
    }
    
    /**
     * Getter flightFrom
     *
     * @return CityEntity
     */
    public CityEntity getFlightFrom() {
        return flightFrom;
    }
    
    /**
     * Setter flightFrom
     *
     * @param flightFrom CityEntity
     */
    public void setFlightFrom(CityEntity flightFrom) {
        this.flightFrom = flightFrom;
    }
    
    /**
     * Getter flightTo
     *
     * @return CityEntity
     */
    public CityEntity getFlightTo() {
        return flightTo;
    }
    
    /**
     * Setter flightTo
     *
     * @param flightTo CityEntity
     */
    public void setFlightTo(CityEntity flightTo) {
        this.flightTo = flightTo;
    }
    
    /**
     * Getter flightStart
     *
     * @return Date
     */
    public Date getFlightStart() {
        return flightStart;
    }

    /**
     * Setter flightStart
     *
     * @param flightStart
     */
    public void setFlightStart(Date flightStart) {
        this.flightStart = flightStart;
    }

    /**
     * Getter flightFinish
     *
     * @return Date
     */
    public Date getFlightFinish() {
        return flightFinish;
    }
    
    /**
     * Setter fligthFinish
     *
     * @param flightFinish
     */
    public void setFlightFinish(Date flightFinish) {
        this.flightFinish = flightFinish;
    }
    
    /**
     * Getter flightPassengers
     *
     * @return int
     */
    public int getFlightPassengers() {
        return flightPassengers;
    }

    /**
     * Setter flightPassengers
     *
     * @param flightPassengers int
     */
    public void setFlightPassengers(int flightPassengers) {
        this.flightPassengers = flightPassengers;
    }

    /**
     * Setter one way
     *
     * @param flightOneWay boolean
     */
    public void setFlightOneWay(boolean flightOneWay) {
        this.flightOneWay = flightOneWay;
    }

    /**
     * Getter one way
     *
     * @return boolean
     */
    public boolean getFlightOneWay() {
        return this.flightOneWay;
    }
    
    /**
     * Getter terms and conditions
     *
     * @return
     */
    public boolean getFlightTermsConditions() {
        return flightTermsConditions;
    }

    /**
     * Setter terms and conditions
     *
     * @param flightTermsConditions
     */
    public void setFlightTermsConditions(boolean flightTermsConditions) {
        this.flightTermsConditions = flightTermsConditions;
    }
    
    /**
     * Getter selectedFlightGo
     *
     * @return Integer
     */
    public Integer getSelectedFlightGo() {
        return selectedFlightGo;
    }

    /**
     * Setter selectedFlightGo
     *
     * @param selectedFlightGo Integer
     */
    public void setSelectedFlightGo(Integer selectedFlightGo) {
        this.selectedFlightGo = selectedFlightGo;
    }

    /**
     * Getter selectedFlightBack
     *
     * @return Integer
     */
    public Integer getSelectedFlightBack() {
        return selectedFlightBack;
    }

    /**
     * Setter selectedFlightBack
     *
     * @param selectedFlightBack Integer
     */
    public void setSelectedFlightBack(Integer selectedFlightBack) {
        this.selectedFlightBack = selectedFlightBack;
    }
}