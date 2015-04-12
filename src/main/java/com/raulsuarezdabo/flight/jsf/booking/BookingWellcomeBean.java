/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.jsf.booking;

import com.mycompany.flight.utils.Utils;
import com.raulsuarezdabo.flight.service.CityService;
import com.raulsuarezdabo.flight.service.CountryService;
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
public class BookingWellcomeBean {
    
    public static int MIN_PASSENGERS = 1;
    public static int MAX_PASSENGERS = 5;
    
    private String now;
    
    /**
     * city service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{cityService}")
    private CityService cityService;
    
    /**
     * country service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{countryService}")
    private CountryService countryService;
    
    /**
     * comes from
     */
    private String flightFrom;
    
    /**
     * goes to
     */
    private String flightTo;
    
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
     * Select passengers
     */
    private List<Integer> passengers;
    
    /**
     * one way or not
     */
    private boolean flightOneWay;
    
    /**
     * Terms and conditions of the flight
     */
    private boolean flightTermsConditions;
    
    /**
     * Creates a new instance of BookingWellcomeBean
     */
    public BookingWellcomeBean() {
    }

    /**
     * Getter of the service city
     * @return  CityService
     */
    public CityService getCityService() {
        return cityService;
    }

    /**
     * Setter of the service city
     * @param cityService 
     */
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Getter country service
     * @return CountryService
     */
    public CountryService getCountryService() {
        return countryService;
    }

    /**
     * Setter countryService
     * @param countryService 
     */
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Getter flightFrom
     * @return  String
     */
    public String getFlightFrom() {
        return flightFrom;
    }

    /**
     * Setter flightFrom
     * @param flightFrom    String 
     */
    public void setFlightFrom(String flightFrom) {
        this.flightFrom = flightFrom;
    }

    /**
     * Getter flightTo
     * @return  String
     */
    public String getFlightTo() {
        return flightTo;
    }

    /**
     * Setter flightTo
     * @param flightTo  String 
     */
    public void setFlightTo(String flightTo) {
        this.flightTo = flightTo;
    }

    /**
     * Getter flightStart
     * @return  Date
     */
    public Date getFlightStart() {
        return flightStart;
    }
    
    /**
     * Convert to string the flightStart for the view
     * @return 
     */
    public String flightStartToString() {
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
        if (this.flightStart == null) {
            return sdf.format(new Date());
        }
        else {
            return sdf.format(this.flightStart);
        }
    }

    /**
     * Setter flightStart
     * @param flightStart 
     */
    public void setFlightStart(Date flightStart) {
        this.flightStart = flightStart;
    }

    /**
     * Getter flightFinish
     * @return  Date
     */
    public Date getFlightFinish() {
        return flightFinish;
    }

    /**
     * Setter fligthFinish
     * @param flightFinish 
     */
    public void setFlightFinish(Date flightFinish) {
        this.flightFinish = flightFinish;
    }

    /**
     * Getter flightPassengers
     * @return  int
     */
    public int getFlightPassengers() {
        return flightPassengers;
    }

    /**
     * Setter flightPassengers
     * @param flightPassengers  int 
     */
    public void setFlightPassengers(int flightPassengers) {
        this.flightPassengers = flightPassengers;
    }

    /**
     * is one way
     * @return  boleean
     */
    public boolean isFlightOneWay() {
        return flightOneWay;
    }

    /**
     * Setter one way
     * @param flightOneWay  boolean 
     */
    public void setFlightOneWay(boolean flightOneWay) {
        this.flightOneWay = flightOneWay;
    }
    
    /**
     * Getter one way
     * @return  boolean
     */
    public boolean getFlightOneWay() {
        return this.flightOneWay;
    }
    
    /**
     * Getter of the string of the current day
     *
     * @return String
     */
    public String getNow() {
        return now;
    }

    /**
     * Setter of the current day string
     *
     * @param now String
     */
    public void setNow(String now) {
        this.now = now;
    }

    /**
     * Getter passengers
     * @return  List of integers
     */
    public List<Integer> getPassengers() {
        return passengers;
    }

    /**
     * Setter list of passengers
     * @param passengers    List
     */
    public void setPassengers(List<Integer> passengers) {
        this.passengers = passengers;
    }
    
    /**
     * Getter terms and conditions
     * @return 
     */
    public boolean getFlightTermsConditions() {
        return flightTermsConditions;
    }

    /**
     * Setter terms and conditions
     * @param flightTermsConditions 
     */
    public void setFlightTermsConditions(boolean flightTermsConditions) {
        this.flightTermsConditions = flightTermsConditions;
    }
    
    @PostConstruct
    public void init() {
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
        this.now = sdf.format(new Date());
        this.passengers = Utils.getIntegerList(BookingWellcomeBean.MIN_PASSENGERS, BookingWellcomeBean.MAX_PASSENGERS, 1);
    }
    
}
