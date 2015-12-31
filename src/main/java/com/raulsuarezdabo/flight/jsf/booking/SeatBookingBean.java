/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.jsf.booking;

import com.mycompany.flight.utils.SessionConstantsName;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import com.raulsuarezdabo.flight.jsf.message.Message;
import com.raulsuarezdabo.flight.pojo.BookingSearchPojo;
import com.raulsuarezdabo.flight.service.FlightService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
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
public class SeatBookingBean {

    /**
     * Airport service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{flightService}")
    private FlightService flightService;

    /**
     * Pojo object to store all data from search booking flight
     */
    private BookingSearchPojo bookingSearchPojo;

    /**
     * Entity seat for storing information on db
     */
    private Set<SeatEntity> seats;
    
    /**
     * Creates a new instance of SeatBookingBean
     */
    public SeatBookingBean() {
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
     * Getter list seats
     *
     * @return List
     */
    public Set<SeatEntity> getSeats() {
        return seats;
    }

    /**
     * Setter list seats
     *
     * @param seats
     */
    public void setSeats(Set<SeatEntity> seats) {
        this.seats = seats;
    }

    /**
     * Method that creates an url to arrive at search page
     *
     * @return String url to search page
     */
    public String flightUrlSelectFlight() {
        DateFormat df = new SimpleDateFormat("M/d/yyyy");
        if (this.bookingSearchPojo.getFlightOneWay() == true) {
            return "/booking-process/results?faces-redirect=true"
                    + "&from=" + this.bookingSearchPojo.getFlightFrom().getId().toString()
                    + "&to=" + this.bookingSearchPojo.getFlightTo().getId().toString()
                    + "&start=" + df.format(this.bookingSearchPojo.getFlightStart())
                    + "&oneway=" + "true"
                    + "&passengers=" + this.bookingSearchPojo.getFlightPassengers();
        } else {
            return "/booking-process/results?faces-redirect=true"
                    + "&from=" + this.bookingSearchPojo.getFlightFrom().getId().toString()
                    + "&to=" + this.bookingSearchPojo.getFlightTo().getId().toString()
                    + "&start=" + df.format(this.bookingSearchPojo.getFlightStart())
                    + "&finish=" + df.format(this.bookingSearchPojo.getFlightFinish())
                    + "&oneway=" + "false"
                    + "&passengers=" + this.bookingSearchPojo.getFlightPassengers();
        }
    }

    /**
     * Method to check if this class is available
     *
     * @param item SeatEntity
     */
    public void checkAvailableFlight(SeatEntity item) {
        if (item.getType() != 0) {
            FlightEntity flighGo = this.flightService.getById(this.bookingSearchPojo.getSelectedFlightGo());
            if (this.flightService.checkAvaliabilty(flighGo, item) == false) {
                String errorMessage = FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("errorSeatNotAvailableMessage");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("seatsForm:seatClassesMessage", message);
            }
            if (this.bookingSearchPojo.getFlightOneWay() == false) {
                FlightEntity flighBack = this.flightService.getById(this.bookingSearchPojo.getSelectedFlightBack());
                if (this.flightService.checkAvaliabilty(flighBack, item) == false) {
                    String errorMessage = FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                            FacesContext.getCurrentInstance(), "msg").getString("errorSeatNotAvailableMessage");
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            errorMessage, errorMessage);
                    FacesContext.getCurrentInstance().addMessage("seatsForm:seatClassesMessage", message);
                }
            }
        }
    }
    
    /**
     * Method that checks if a flight has the current seats available
     * @param flight    FlightEntity
     * @param seats Set of seats
     * @return  boolean
     */
    public boolean checkAvailableFlight(FlightEntity flight, Set <SeatEntity> seats) {
        for (SeatEntity seat: seats) {
            if (this.flightService.checkAvaliabilty(flight, seat) == false) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Method to know if is a offer seat available or not
     * @return  boolean
     */
    public boolean isOffer() {
        try {
            if (this.flightService.getById(this.bookingSearchPojo.getSelectedFlightGo()).isOffer()) {
                return true;
            }
            if (this.flightService.getById(this.bookingSearchPojo.getSelectedFlightBack()).isOffer()) {
                return true;
            }
            return false;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @PostConstruct
    public void init() {
        this.bookingSearchPojo = new BookingSearchPojo();
        this.seats = new HashSet<>();

        this.bookingSearchPojo = (BookingSearchPojo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(SessionConstantsName.BOOKINGSEARCH);

        for (int i = 0; i < this.bookingSearchPojo.getFlightPassengers(); i++) {
            SeatEntity seat = new SeatEntity();
            this.seats.add(seat);
        }
        // Hook that checks if you have no seats to select
        if (this.seats.isEmpty() == true) {
            this.warningRedirect();
        }
    }

    /**
     * Method that generates an automatic redirect and clean memcache booking
     * node
     */
    private void warningRedirect() {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER,
                FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("errorSeatsSystemMessage")
        );
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().
                handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml?faces-redirect=true");
    }

    /**
     * Action button form
     *
     * @return String with the url to apply
     */
    public String submitAction() {
        FlightEntity flighGo = this.flightService.getById(this.bookingSearchPojo.getSelectedFlightGo());

        if (flighGo == null) {
            this.warningRedirect();
        }
        if (this.checkAvailableFlight(flighGo, seats) == false) {
            return "";
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(SessionConstantsName.INFOFLIGHTGO, flighGo);
        if (this.bookingSearchPojo.getFlightOneWay() == false) {
            FlightEntity flightBack = this.flightService.getById(this.bookingSearchPojo.getSelectedFlightBack());
            if (flightBack == null) {
                this.warningRedirect();
            }
            if (this.checkAvailableFlight(flightBack, seats) == false) {
                return "";
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(SessionConstantsName.INFOFLIGHTBACK, flightBack);
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(SessionConstantsName.INFOSEATS, this.seats);
        
        return "/booking-process/resum?faces-redirect=true";
    }

}
