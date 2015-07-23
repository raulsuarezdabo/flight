package com.raulsuarezdabo.flight.jsf.booking;

import com.mycompany.flight.service.UserService;
import com.mycompany.flight.utils.SessionConstantsName;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import com.raulsuarezdabo.flight.jsf.message.Message;
import com.raulsuarezdabo.flight.pojo.BookingSearchPojo;
import com.raulsuarezdabo.flight.service.FlightService;
import com.raulsuarezdabo.flight.service.SeatService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
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

    @Autowired
    @ManagedProperty(value = "#{userService}")
    private UserService userService;

    @Autowired
    @ManagedProperty(value = "#{seatService}")
    private SeatService seatService;

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
     * Getter seatService
     *
     * @return SeatService
     */
    public SeatService getSeatService() {
        return seatService;
    }

    /**
     * Setter seatService
     *
     * @param seatService
     */
    public void setSeatService(SeatService seatService) {
        this.seatService = seatService;
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
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(SessionConstantsName.BOOKINGSEARCH);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER,
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                            FacesContext.getCurrentInstance(), "msg").getString("errorSeatsSystemMessage")
            );
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().
                    handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml?faces-redirect=true");
        }
    }

    /**
     * Action button form
     *
     * @return String with the url to apply
     */
    public String submitAction() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(SessionConstantsName.BOOKINGSEARCH);
        FlightEntity flighGo = this.flightService.getById(this.bookingSearchPojo.getSelectedFlightGo());

        if (flighGo == null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER,
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                            FacesContext.getCurrentInstance(), "msg").getString("errorSeatsSystemMessage")
            );

            FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
                    .handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml?faces-redirect=true");
        }

        if (this.bookingSearchPojo.getFlightOneWay() == false) {
            FlightEntity flightBack = this.flightService.getById(this.bookingSearchPojo.getSelectedFlightBack());
            if (flightBack == null) {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER,
                        FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                                FacesContext.getCurrentInstance(), "msg").getString("errorSeatsSystemMessage")
                );

                FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
                        .handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml?faces-redirect=true");
            }
        }
        
        

        return "";
    }

}
