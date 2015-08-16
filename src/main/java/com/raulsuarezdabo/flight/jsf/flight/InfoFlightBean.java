
package com.raulsuarezdabo.flight.jsf.flight;

import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.service.FlightService;
import static java.lang.Integer.parseInt;
import java.util.Date;
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
public class InfoFlightBean {
    
    FlightEntity flight;

    /**
     * Airport service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{flightService}")
    private FlightService flightService;
    
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
     * Getter flight
     * @return  FlightEntity
     */
    public FlightEntity getFlight() {
        return flight;
    }

    /**
     * Setter flight
     * @param flight    FlightEntity 
     */
    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }
    
    /**
     * Sum initial date to time of flight
     *
     * @param init Date
     * @param time Date
     * @return Date
     */
    public Date flightFinish(Date init, Date time) {
        if (init != null && time != null) {
            return new Date(init.getTime() + time.getTime());
        }
        return null;
    }
    
    @PostConstruct
    public void init() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer id = (Integer)parseInt(parameterMap.get("parameter"));
        this.flight = this.flightService.getById(id);
    }
    
}
