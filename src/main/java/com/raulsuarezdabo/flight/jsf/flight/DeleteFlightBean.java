package com.raulsuarezdabo.flight.jsf.flight;

import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.jsf.message.Message;
import com.raulsuarezdabo.flight.service.AirplaneService;
import com.raulsuarezdabo.flight.service.AirportService;
import com.raulsuarezdabo.flight.service.FlightService;
import static java.lang.Integer.parseInt;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
public class DeleteFlightBean {

    /**
     * Airport service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{flightService}")
    private FlightService flightService;

    @Autowired
    @ManagedProperty(value = "#{airportService}")
    private AirportService airportService;

    @Autowired
    @ManagedProperty(value = "#{airplaneService}")
    private AirplaneService airplaneService;

    /**
     * id of the flight
     */
    private int id;

    /**
     * Airport to
     */
    private int airportFrom;

    /**
     * AirportTo
     */
    private int airportTo;

    /**
     * Airplane
     */
    private int airplane;

    /**
     * It takes off
     */
    private Date start;

    /**
     * time of the flight
     */
    private Date time;

    /**
     * Airports list
     */
    private List<AirportEntity> airports;

    /**
     * Airplanes list
     */
    private List<AirplaneEntity> airplanes;

    /**
     * Creates a new instance of AddFlightBean
     */
    public DeleteFlightBean() {
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
     * Getter airportService
     *
     * @return AirportService
     */
    public AirportService getAirportService() {
        return airportService;
    }

    /**
     * Setter airportService
     *
     * @param airportService AirportService
     */
    public void setAirportService(AirportService airportService) {
        this.airportService = airportService;
    }

    /**
     * Getter airportFrom
     *
     * @return AirportEntity
     */
    public int getAirportFrom() {
        return airportFrom;
    }

    /**
     * Setter airportFrom
     *
     * @param airportFrom AirportEntity
     */
    public void setAirportFrom(int airportFrom) {
        this.airportFrom = airportFrom;
    }

    /**
     * Getter airportTo
     *
     * @return AirportEntity
     */
    public int getAirportTo() {
        return airportTo;
    }

    /**
     * Setter airportTo
     *
     * @param airportTo AirportEntity
     */
    public void setAirportTo(int airportTo) {
        this.airportTo = airportTo;
    }

    /**
     * Getter airplane
     *
     * @return int
     */
    public int getAirplane() {
        return airplane;
    }

    /**
     * Setter airpolane
     *
     * @param airplane int
     */
    public void setAirplane(int airplane) {
        this.airplane = airplane;
    }

    /**
     * Getter Start
     *
     * @return Date
     */
    public Date getStart() {
        return start;
    }

    /**
     * Setter start
     *
     * @param start Date
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * Getter Time
     *
     * @return Date time
     */
    public Date getTime() {
        return time;
    }

    /**
     * Setter Time
     *
     * @param time Time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Getter airports list
     *
     * @return List of airports
     */
    public List<AirportEntity> getAirports() {
        return airports;
    }

    /**
     * Setter airports
     *
     * @param airports List of airports available
     */
    public void setAirports(List<AirportEntity> airports) {
        this.airports = airports;
    }

    /**
     * Getter airplaneService
     *
     * @return AirplaneService
     */
    public AirplaneService getAirplaneService() {
        return airplaneService;
    }

    /**
     * Setter airplaneService
     *
     * @param airplaneService AirplaneService
     */
    public void setAirplaneService(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    /**
     * Getter airplanes list
     *
     * @return List
     */
    public List<AirplaneEntity> getAirplanes() {
        return airplanes;
    }

    /**
     * Setter airplanes
     *
     * @param airplanes List
     */
    public void setAirplanes(List<AirplaneEntity> airplanes) {
        this.airplanes = airplanes;
    }

    @PostConstruct
    public void init() {
        this.airports = this.airportService.getAll();
        this.airplanes = this.airplaneService.getAll();

        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.id = parseInt(parameterMap.get("parameter"));
        FlightEntity flight = this.flightService.getById(this.id);
        if (flight != null && flight instanceof FlightEntity) {
            this.airportFrom = flight.getAirportFrom().getId();
            this.airportTo = flight.getAirportTo().getId();
            this.airplane = flight.getAirplane().getId();
            this.start = flight.getStart();
            this.time = flight.getTime();
        }
    }

    /**
     * Method for adding new flight with the required atributes
     *
     * @return String with the redirection to the correct url
     */
    public String deleteFlightAction() {
        try {
            if (this.flightService.deleteFlight(this.id) == true) {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.SUCCESS,
                        FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                                FacesContext.getCurrentInstance(), "msg").getString("deleteSuccessFlightMessage")
                );
            } else {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER,
                        FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                                FacesContext.getCurrentInstance(), "msg").getString("deleteDangerFlightMessage")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/flight/index?faces-redirect=true";
    }

}
