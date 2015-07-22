/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.jsf.booking;

import com.mycompany.flight.service.UserService;
import com.mycompany.flight.utils.SessionConstantsName;
import com.mycompany.flight.utils.Utils;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.pojo.BookingSearchPojo;
import com.raulsuarezdabo.flight.service.CityService;
import com.raulsuarezdabo.flight.service.CountryService;
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
import org.springframework.util.SerializationUtils;

/**
 *
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class BookingSearchBean {

    public static int MIN_PASSENGERS = 1;
    public static int MAX_PASSENGERS = 5;

    private String now;

    /**
     * city service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;

    /**
     * country service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;

    /**
     * Airport service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{flightService}")
    private FlightService flightService;

    @Autowired
    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    /**
     * Select passengers
     */
    private List<Integer> passengers;

    /**
     * Flight list from to
     */
    private List<FlightEntity> flightsGo;

    /**
     * Flight list to from
     */
    private List<FlightEntity> flightsBack;

    /**
     * Pojo object to store all data from search booking flight
     */
    private BookingSearchPojo bookingSearchPojo;

    /**
     * Creates a new instance of BookingWellcomeBean
     */
    public BookingSearchBean() {
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
     * Getter country service
     *
     * @return CountryService
     */
    public CountryService getCountryService() {
        return countryService;
    }

    /**
     * Setter countryService
     *
     * @param countryService
     */
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

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
     * Getter of the userService
     *
     * @return
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Setter of the userService
     *
     * @param userService
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
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
     * Getter bookingSearchPojo
     *
     * @return BookingSearchPojo
     */
    public BookingSearchPojo getBookingSearchPojo() {
        return bookingSearchPojo;
    }

    /**
     * Setter bookingSearchPojo
     *
     * @param bookingSearchPojo
     */
    public void setBookingSearchPojo(BookingSearchPojo bookingSearchPojo) {
        this.bookingSearchPojo = bookingSearchPojo;
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
     * Method for filtering the result for searching city where start or finish
     *
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
     * is one way
     *
     * @return boleean
     */
    public boolean isFlightOneWay() {
        return this.bookingSearchPojo.getFlightOneWay();
    }

    /**
     * Method for checking if origin and destiniy are the same.
     *
     * @return boolean with true or false if it's those are the same.
     */
    private boolean sameOriginDestiny() {
        if (this.bookingSearchPojo.getFlightTo() != null && this.bookingSearchPojo.getFlightFrom() != null) {
            return this.bookingSearchPojo.getFlightTo().equals(this.bookingSearchPojo.getFlightFrom());
        }
        return false;
    }

    /**
     * Method that checks if city origin isn't the same as origin
     */
    public void checkFlightFrom() {
        if (this.bookingSearchPojo.getFlightTo() != null) {
            if (this.sameOriginDestiny() == true) {
                String errorMessage = FacesContext.getCurrentInstance().getApplication().
                        getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("toequalsFrom");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("resultForm:flightFrom", message);
            }
        }
    }

    /**
     * Method that checks if city Destiny isn't the same as origin
     */
    public void checkFlightTo() {
        if (this.bookingSearchPojo.getFlightFrom() != null) {
            if (this.sameOriginDestiny() == true) {
                String errorMessage = FacesContext.getCurrentInstance().getApplication().
                        getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("toequalsFrom");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("resultForm:flightTo", message);
            }
        }
    }

    /**
     * Method that checks if start date isn't correct with finish date
     */
    public void checkFlightStart() {
        if (this.bookingSearchPojo.getFlightOneWay() == false && this.bookingSearchPojo.getFlightFinish() != null) {
            if (this.bookingSearchPojo.getFlightStart().before(this.bookingSearchPojo.getFlightFinish()) == false) {
                String errorMessage = FacesContext.getCurrentInstance().getApplication().
                        getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("flightStartFinishError");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("resultForm:flightStart", message);
            }
        }
    }

    /**
     * Method that checks if finish date isn't correct with start date
     */
    public void checkFlightFinish() {
        if (this.bookingSearchPojo.getFlightOneWay() == false && this.bookingSearchPojo.getFlightStart() != null) {
            if (this.bookingSearchPojo.getFlightStart().before(this.bookingSearchPojo.getFlightFinish()) == false) {
                String errorMessage = FacesContext.getCurrentInstance().getApplication().
                        getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("flightStartFinishError");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("resultForm:flightFinish", message);
            }
        }
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
        if (this.bookingSearchPojo.getFlightOneWay() == false) {
            if (this.bookingSearchPojo.getFlightFinish() != null && this.bookingSearchPojo.getFlightStart().before(this.bookingSearchPojo.getFlightFinish()) == true) {
                return "/booking-process/results?faces-redirect=true"
                        + "&from=" + this.bookingSearchPojo.getFlightFrom().getId().toString()
                        + "&to=" + this.bookingSearchPojo.getFlightTo().getId().toString()
                        + "&start=" + df.format(this.bookingSearchPojo.getFlightStart())
                        + "&finish=" + df.format(this.bookingSearchPojo.getFlightFinish())
                        + "&oneway=" + "false"
                        + "&passengers=" + this.bookingSearchPojo.getFlightPassengers();
            }
            String errorMessage = FacesContext.getCurrentInstance().getApplication().
                    getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("flightStartFinishError");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("resultForm:flightStart", message);
            return "";
        } else {
            return "/booking-process/results?faces-redirect=true"
                    + "&from=" + this.bookingSearchPojo.getFlightFrom().getId().toString()
                    + "&to=" + this.bookingSearchPojo.getFlightTo().getId().toString()
                    + "&start=" + df.format(this.bookingSearchPojo.getFlightStart())
                    + "&oneway=" + "true"
                    + "&passengers=" + this.bookingSearchPojo.getFlightPassengers();
        }
    }
    
    /**
     * Method for submiting flight selected from form
     *
     * @return  String  direction to apply
     */
    public String bookAction() {
        if (this.bookingSearchPojo.getFlightOneWay() == true) {
            if (this.bookingSearchPojo.getSelectedFlightGo() != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                    SessionConstantsName.BOOKINGSEARCH, this.bookingSearchPojo);
                return this.checklogged();
            } else {
                String errorMessage = FacesContext.getCurrentInstance().getApplication().
                        getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("flightGoRequired");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("resultForm:flightGoId", message);
            }
        } else {
            if (this.bookingSearchPojo.getSelectedFlightGo() == null || this.bookingSearchPojo.getSelectedFlightBack() == null) {
                if (this.bookingSearchPojo.getSelectedFlightGo() == null) {
                    String errorMessage = FacesContext.getCurrentInstance().getApplication().
                            getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("flightGoRequired");
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            errorMessage, errorMessage);
                    FacesContext.getCurrentInstance().addMessage("resultForm:flightGoId", message);
                }
                if (this.bookingSearchPojo.getSelectedFlightBack() == null) {
                    String errorMessage = FacesContext.getCurrentInstance().getApplication().
                            getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("flightBackRequired");
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            errorMessage, errorMessage);
                    FacesContext.getCurrentInstance().addMessage("resultForm:flightBackId", message);
                }
            }
            else {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                    SessionConstantsName.BOOKINGSEARCH, this.bookingSearchPojo);
                return this.checklogged();
            }
        }
        return "";
    }
    
    /**
     * Private method that check if user is logged and then apply the correct redirection
     * @return  String redirection to apply
     */
    private String checklogged() {
        if (this.userService.isLogged() == true) {
            return "/booking-process/seats?faces-redirect=true";
        } else {
            return "/register/also-have-account?faces-redirect=true";
        }
    }

    @PostConstruct
    public void init() {
        this.bookingSearchPojo = new BookingSearchPojo();
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
        this.now = sdf.format(new Date());
        this.passengers = Utils.getIntegerList(BookingSearchBean.MIN_PASSENGERS, BookingSearchBean.MAX_PASSENGERS, 1);
        if (FacesContext.getCurrentInstance().getViewRoot().getViewId().equals("/booking-process/results.xhtml") == true) {
            Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            try {
                if (parameterMap.containsKey("oneway") == false) {
                    throw new ParseException("Not found one way field", 0);
                }
                this.bookingSearchPojo.setFlightOneWay(Boolean.valueOf(parameterMap.get("oneway")));
                if (parameterMap.containsKey("from") == false || parameterMap.containsKey("to") == false) {
                    throw new ParseException("Not found from or to field or fields", 0);
                }
                this.bookingSearchPojo.setFlightFrom(this.cityService.getById(Integer.parseInt(parameterMap.get("from"))));
                this.bookingSearchPojo.setFlightTo(this.cityService.getById(Integer.parseInt(parameterMap.get("to"))));
                if (parameterMap.containsKey("passengers") == false) {
                    throw new ParseException("Not found num passengers fields", 0);
                }
                this.bookingSearchPojo.setFlightPassengers(Integer.parseInt(parameterMap.get("passengers")));
                if (parameterMap.containsKey("start") == false) {
                    throw new ParseException("Not found date to start", 0);
                }
                this.bookingSearchPojo.setFlightStart(sdf.parse(parameterMap.get("start")));
                if (this.bookingSearchPojo.getFlightOneWay() == false) {
                    if (parameterMap.containsKey("finish") == false) {
                        throw new ParseException("Not found date to come back", 0);
                    }
                    this.bookingSearchPojo.setFlightFinish(sdf.parse(parameterMap.get("finish")));
                }
                this.flightsGo = this.flightService.searchFlights(
                        this.bookingSearchPojo.getFlightFrom(),
                        this.bookingSearchPojo.getFlightTo(),
                        this.bookingSearchPojo.getFlightStart(),
                        this.bookingSearchPojo.getFlightPassengers());
                if (this.bookingSearchPojo.getFlightOneWay() == false) {
                    this.flightsBack = this.flightService.searchFlights(
                            this.bookingSearchPojo.getFlightFrom(),
                            this.bookingSearchPojo.getFlightTo(),
                            this.bookingSearchPojo.getFlightFinish(),
                            this.bookingSearchPojo.getFlightPassengers());
                }
            } catch (ParseException ex) {
                this.flightsGo = new ArrayList();
                this.flightsBack = new ArrayList();
            } catch (Exception e) {
                this.flightsGo = new ArrayList();
                this.flightsBack = new ArrayList();
            }
        }
    }

}
