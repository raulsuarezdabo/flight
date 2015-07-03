/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.jsf.booking;

import com.mycompany.flight.utils.Utils;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.service.CityService;
import com.raulsuarezdabo.flight.service.FlightService;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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
public class BookingResultBean {

    private String now;

    /**
     * Airport service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{flightService}")
    private FlightService flightService;

    /**
     * city service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;

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
     * Flight list from to
     */
    private List<FlightEntity> flightsGo;
    
    /**
     * Flight list to from
     */
    private List<FlightEntity> flightsBack;
    
    /**
     * Id for selected flight to go
     */
    private Integer selectedFlightGo;
    
    /**
     * Id for selected flight to come back
     */
    private Integer selectedFlightBack;

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
     *
     * @return List of flights
     */
    public List<FlightEntity> getFlightsGo() {
        return flightsGo;
    }

    /**
     * Setter flights
     *
     * @param flights List of flights
     */
    public void setFlightsGo(List<FlightEntity> flights) {
        this.flightsGo = flights;
    }
    
    /**
     * Getter flights
     *
     * @return List of flights
     */
    public List<FlightEntity> getFlightsBack() {
        return flightsBack;
    }

    /**
     * Setter flights
     *
     * @param flights List of flights
     */
    public void setFlightsBack(List<FlightEntity> flights) {
        this.flightsBack = flights;
    }

    /**
     * Getter of the service city
     *
     * @return CityService
     */
    public CityService getCityService() {
        return cityService;
    }

    /**
     * Setter of the service city
     *
     * @param cityService
     */
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Getter flightFrom
     *
     * @return String
     */
    public CityEntity getFlightFrom() {
        return flightFrom;
    }

    /**
     * Setter flightFrom
     *
     * @param flightFrom String
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
        if (this.flightOneWay == false && this.flightStart != null) {
            if (this.flightStart.before(flightFinish) == true) {
                this.flightFinish = flightFinish;
            } else {
                String errorMessage = FacesContext.getCurrentInstance().getApplication().
                        getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("flightStartFinishError");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("wellcomeForm:flightFinish", message);
                this.flightFinish = null;
            }
        } else {
            this.flightFinish = flightFinish;
        }
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
     * is one way
     *
     * @return boleean
     */
    public boolean isFlightOneWay() {
        return flightOneWay;
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
     *
     * @return List of integers
     */
    public List<Integer> getPassengers() {
        return passengers;
    }

    /**
     * Setter list of passengers
     *
     * @param passengers List
     */
    public void setPassengers(List<Integer> passengers) {
        this.passengers = passengers;
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
     * @return Integer
     */
    public Integer getSelectedFlightGo() {
        return selectedFlightGo;
    }

    /**
     * Setter selectedFlightGo
     * @param selectedFlightGo  Integer 
     */
    public void setSelectedFlightGo(Integer selectedFlightGo) {
        this.selectedFlightGo = selectedFlightGo;
    }

    /**
     * Getter selectedFlightBack
     * @return Integer
     */
    public Integer getSelectedFlightBack() {
        return selectedFlightBack;
    }

    /**
     * Setter selectedFlightBack
     * @param selectedFlightBack    Integer 
     */
    public void setSelectedFlightBack(Integer selectedFlightBack) {
        this.selectedFlightBack = selectedFlightBack;
    }

    /**
     * Creates a new instance of BookingResultBean
     */
    public BookingResultBean() {
    }
    
    /**
     * Method for filtering the result for searching city where start or finish
     * @param query String
     * @return List with suggested cities
     */
    public List<CityEntity> completeCity(String query) {
        List<CityEntity> allCities = this.cityService.getAll();
        List<CityEntity> filteredCities = new ArrayList<>();
        
        for (CityEntity city : allCities) {
            if (city.getName().toLowerCase().startsWith(query)) {
                filteredCities.add(city);
            }
        }
        return filteredCities;
    }
    
    /**
     * Method for checking if origin and destiniy are the same.
     *
     * @return boolean with true or false if it's those are the same.
     */
    private boolean sameOriginDestiny() {
        if (this.flightTo != null && this.flightFrom != null) {
            return this.flightTo.equals(this.flightFrom);
        }
        return false;
    }
    
    /**
     * Method for submiting the forms
     *
     * @return direction to apply
     * @throws java.io.UnsupportedEncodingException
     */
    public String submitAction() throws UnsupportedEncodingException {
        //Checks if origin and destiny are the same one.
        if (this.sameOriginDestiny() == true) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("M/d/yyyy");
        //Check dates coherence
        if (this.flightOneWay == false) {
            if (this.flightFinish != null && this.flightStart.before(this.flightFinish) == true) {
                return "/booking-process/results?faces-redirect=true"
                    + "&from=" + this.flightFrom.getId().toString()
                    + "&to=" + this.flightTo.getId().toString()
                    + "&start=" + df.format(this.flightStart)
                    + "&finish=" + df.format(this.flightFinish)
                    + "&oneway=" + "false"
                    + "&passengers=" + this.flightPassengers
                        ;
            }
            String errorMessage = FacesContext.getCurrentInstance().getApplication().
                    getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("flightStartFinishError");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("wellcomeForm:flightStart", message);
            return "";
        } else {
            return "/booking-process/results?faces-redirect=true"
                + "&from=" + this.flightFrom.getId().toString()
                + "&to=" + this.flightTo.getId().toString()
                + "&start=" + df.format(this.flightStart)
                + "&oneway=" + "true"
                + "&passengers=" + this.flightPassengers
                    ;
        }
    }

    @PostConstruct
    public void init() {
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
        this.now = sdf.format(new Date());
        this.passengers = Utils.getIntegerList(BookingWellcomeBean.MIN_PASSENGERS, BookingWellcomeBean.MAX_PASSENGERS, 1);

        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            if (parameterMap.containsKey("oneway") == false) {
                throw new ParseException("Not found one way field", 0);
            }
            this.flightOneWay = Boolean.valueOf(parameterMap.get("oneway"));
            if (parameterMap.containsKey("from") == false || parameterMap.containsKey("to") == false) {
                throw new ParseException("Not found from or to field or fields", 0);
            }
            this.flightFrom = this.cityService.getById(Integer.parseInt(parameterMap.get("from")));
            this.flightTo = this.cityService.getById(Integer.parseInt(parameterMap.get("to")));
            if (parameterMap.containsKey("passengers") == false){
                throw new ParseException("Not found num passengers fields", 0);
            }
            this.flightPassengers = Integer.parseInt(parameterMap.get("passengers"));
            if (parameterMap.containsKey("start") == false) {
                throw new ParseException("Not found date to start", 0);
            }
            this.flightStart = sdf.parse(parameterMap.get("start"));
            if (this.flightOneWay == false) {
                if (parameterMap.containsKey("finish") == false) {
                    throw new ParseException("Not found date to come back", 0);
                }
                this.flightFinish = sdf.parse(parameterMap.get("finish"));
            }
            this.flightsGo = this.flightService.searchFlights(this.flightFrom, this.flightTo, this.flightStart, this.flightPassengers);
            if (this.flightOneWay == false) {
                this.flightsBack = this.flightService.searchFlights(this.flightFrom, this.flightTo, this.flightFinish, this.flightPassengers);
            }
        } catch (ParseException ex) {
            this.flightsGo = new ArrayList();
            this.flightsBack = new ArrayList();
        }
        catch (Exception e) {
            this.flightsGo = new ArrayList();
            this.flightsBack = new ArrayList();
        }
    }
}
