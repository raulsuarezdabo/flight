
package com.raulsuarezdabo.flight.jsf.booking;

import com.mycompany.flight.utils.SessionConstantsName;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import com.raulsuarezdabo.flight.jsf.message.Message;
import com.raulsuarezdabo.flight.pojo.BookingSearchPojo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class ResumeBookingBean {
    
    /**
     * Pojo object to store all data from search booking flight
     */
    private BookingSearchPojo bookingSearchPojo;
    
    /**
     * Entity seat for storing information on db
     */
    private Set<SeatEntity> seats;
    
    /**
     * Entity Flight to create the book
     */
    private FlightEntity flightGo;
    
    /**
     * Entity Flight to create the book
     */
    private FlightEntity flighBack;

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
     * Getter flightGo
     * @return  FlightEntity
     */
    public FlightEntity getFlightGo() {
        return flightGo;
    }

    /**
     * Setter flightGo
     * @param flightGo  FlightEntity 
     */
    public void setFlightGo(FlightEntity flightGo) {
        this.flightGo = flightGo;
    }

    /**
     * Getter flightBack
     * @return  FlightEntity
     */
    public FlightEntity getFlighBack() {
        return flighBack;
    }

    /**
     * Setter flightBack
     * @param flighBack     FlightEntity
     */
    public void setFlighBack(FlightEntity flighBack) {
        this.flighBack = flighBack;
    }
    
    /**
     * Creates a new instance of ResumeBookingBean
     */
    public ResumeBookingBean() {
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
     * Sum initial date to time of flight
     * @param init  Date
     * @param time  Date
     * @return  Date
     */
    public Date flightFinish(Date init, Date time) {
        if (init != null && time != null) {
            return new Date(init.getTime() + time.getTime());
        }
        return null;
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
    
    @PostConstruct
    void init() {
        this.bookingSearchPojo = new BookingSearchPojo();
        this.bookingSearchPojo = (BookingSearchPojo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(SessionConstantsName.BOOKINGSEARCH);
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey(SessionConstantsName.INFOSEATS) == false) {
            this.warningRedirect();
        }
        this.seats = (Set) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(SessionConstantsName.INFOSEATS);
        
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey(SessionConstantsName.INFOFLIGHTGO) == false) {
            this.warningRedirect();
        }
        this.flightGo = (FlightEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(SessionConstantsName.INFOFLIGHTGO);
        
        if (this.bookingSearchPojo != null) {
            if (this.bookingSearchPojo.getFlightOneWay() == false && 
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey(SessionConstantsName.INFOFLIGHTBACK) == false) {
                this.warningRedirect();
            }
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey(SessionConstantsName.INFOFLIGHTBACK) == true) {
            this.flighBack = (FlightEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(SessionConstantsName.INFOFLIGHTBACK);
        }   
    }
    
    /**
     * Method that submit the actual action
     * @return  String
     */
    public String bookAction() {
        return "";
    }
    
}
