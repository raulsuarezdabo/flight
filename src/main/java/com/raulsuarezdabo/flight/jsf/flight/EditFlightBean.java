
package com.raulsuarezdabo.flight.jsf.flight;

import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.jsf.message.Message;
import com.raulsuarezdabo.flight.service.AirplaneService;
import com.raulsuarezdabo.flight.service.AirportService;
import com.raulsuarezdabo.flight.service.FlightService;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
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
public class EditFlightBean {

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

    private String now;
    
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
    public EditFlightBean() {
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
        AirportEntity airport = this.airportService.getById(airportFrom);
        if (airport != null && airport instanceof AirportEntity) {
            if (this.airportTo != 0 && this.airportTo == airport.getId()) {
                String errorMessage = FacesContext.getCurrentInstance().getApplication().
                        getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("AirportEquals");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("flightForm:flightAirportFrom", message);
                this.airportFrom = 0;
            }
            else {
                this.airportFrom = airportFrom;
            }
        } else {
            // Bring the error message using the Faces Context
            String errorMessage = FacesContext.getCurrentInstance().getApplication().
                    getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("AirportInvalid");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("flightForm:flightAirportFrom", message);
            this.airportFrom = 0;
        }
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
        AirportEntity airport = this.airportService.getById(airportTo);
        if (airport != null && airport instanceof AirportEntity) {
            if (this.airportFrom != 0 && this.airportFrom == airport.getId()) {
                String errorMessage = FacesContext.getCurrentInstance().getApplication().
                        getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("AirportEquals");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("flightForm:flightAirportTo", message);
                this.airportTo = 0;
            }
            else {
                this.airportTo = airportTo;
            }
        } else {
            // Bring the error message using the Faces Context
            String errorMessage = FacesContext.getCurrentInstance().getApplication().
                    getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("AirportInvalid");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("flightForm:flightAirportTo", message);
            this.airportFrom = 0;
        }
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
        AirplaneEntity airplaneModel = this.airplaneService.getById(airplane);
        if (airplaneModel != null && airplaneModel instanceof AirplaneEntity) {
            this.airplane = airplane;
        } else {
            String errorMessage = FacesContext.getCurrentInstance().getApplication().
                    getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("AirplaneInvalid");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("flightForm:flightAirplane", message);
            this.airportFrom = 0;
        }
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
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
        this.now = sdf.format(new Date());
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
    public String editFlightAction() {
        try {
            AirportEntity airportFromE = this.airportService.getById(this.airportFrom);
            AirportEntity airportToE = this.airportService.getById(this.airportTo);
            AirplaneEntity airplaneE = this.airplaneService.getById(this.airplane);
            if ((airplaneE instanceof AirplaneEntity) && (airportFromE instanceof AirportEntity) && (airportToE instanceof AirportEntity)) {
                FlightEntity flightE = new FlightEntity();
                flightE.setAirportFrom(airportFromE);
                flightE.setAirportTo(airportToE);
                flightE.setAirplane(airplaneE);
                flightE.setStart(this.start);
                flightE.setTime(this.time);
                FlightEntity flight = this.flightService.updateFlight(this.id, flightE, true);
                if (flight != null && flight instanceof FlightEntity) {
                    FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.SUCCESS,
                            FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                                    FacesContext.getCurrentInstance(), "msg").getString("editSuccessFlightMessage")
                    );
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER,
                            FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                                    FacesContext.getCurrentInstance(), "msg").getString("editDangerFlightMessage")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/flight/index?faces-redirect=true";
    }

}
