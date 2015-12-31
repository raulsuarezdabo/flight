/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.jsf.airplane;

import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import com.raulsuarezdabo.flight.jsf.message.Message;
import com.raulsuarezdabo.flight.service.AirplaneService;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Bean for managing new airplane
 *
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class DeleteAirplaneBean {

    /**
     * Airline service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{airplaneService}")
    private AirplaneService airplaneService;

    /**
     * Creates a new instance of AddAirplane
     */
    public DeleteAirplaneBean() {
    }
    
    /**
     * id int
     */
    private int id;

    /**
     * Model of the airplane
     */
    private String model;

    /**
     * maker of the airplane
     */
    private String maker;

    /**
     * year's airplane
     */
    private Integer year;

    private List<Integer> years;

    /**
     * total seats
     */
    private Integer numSeatsTotal;

    /**
     * number offer seats
     */
    private Integer numSeatsOffer;

    /**
     * number tourist seats
     */
    private Integer numSeatsTourist;

    /**
     * number business seats
     */
    private Integer numSeatsBusiness;

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
     * Getter model
     *
     * @return String
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter model
     *
     * @param model String
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter maker
     *
     * @return String
     */
    public String getMaker() {
        return maker;
    }

    /**
     * Setter maker
     *
     * @param maker String
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    /**
     * Getter year
     *
     * @return Integer
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Setter year
     *
     * @param year Integer
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Getter num total seats
     *
     * @return Integer
     */
    public Integer getNumSeatsTotal() {
        return numSeatsTotal;
    }

    /**
     * Setter num total seats
     *
     * @param numSeatsTotal Integer
     */
    public void setNumSeatsTotal(Integer numSeatsTotal) {
        this.numSeatsTotal = numSeatsTotal;
    }

    /**
     * Getter num offer seats
     *
     * @return Integer
     */
    public Integer getNumSeatsOffer() {
        return numSeatsOffer;
    }

    /**
     * Setter number of offer seats
     *
     * @param numSeatsOffer Integer
     */
    public void setNumSeatsOffer(Integer numSeatsOffer) {
        this.numSeatsOffer = numSeatsOffer;
    }

    /**
     * Getter num seats tourist
     *
     * @return Integer
     */
    public Integer getNumSeatsTourist() {
        return numSeatsTourist;
    }

    /**
     * Setter num seats tourist
     *
     * @param numSeatsTourist Integer
     */
    public void setNumSeatsTourist(Integer numSeatsTourist) {
        this.numSeatsTourist = numSeatsTourist;
    }

    /**
     * Getter number seats business
     *
     * @return Integer
     */
    public Integer getNumSeatsBusiness() {
        return numSeatsBusiness;
    }

    /**
     * Setter number seats business
     *
     * @param numSeatsBusiness Integer
     */
    public void setNumSeatsBusiness(Integer numSeatsBusiness) {
        this.numSeatsBusiness = numSeatsBusiness;
    }

    /**
     * Getter years
     *
     * @return List list of years
     */
    public List<Integer> getYears() {
        return years;
    }

    /**
     * Setter years
     *
     * @param years List of years
     */
    public void setYears(List<Integer> years) {
        this.years = years;
    }

    @PostConstruct
    public void init() {
        this.years = new ArrayList();
        for (int i = Calendar.getInstance().get(Calendar.YEAR); i > 1980; i--) {
            this.years.add(i);
        }
        
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.id = parseInt(parameterMap.get("parameter"));
        AirplaneEntity airplane = this.airplaneService.getById(id);
        if (airplane != null && (airplane instanceof AirplaneEntity) == true) {
            this.maker = airplane.getMaker();
            this.model = airplane.getModel();
            this.year = airplane.getYear();
            this.numSeatsTotal = airplane.getNumSeatsTotal();
            this.numSeatsOffer = airplane.getNumSeatsOffer();
            this.numSeatsTourist = airplane.getNumSeatsTourist();
            this.numSeatsBusiness = airplane.getNumSeatsBusiness();
        }
    }
    /**
     * Action method for adding new airplane to the system
     * @return  String  with the redirection to main page
     */
    public String deleteAirplaneAction() {
        try {
            if (this.airplaneService.deleteAirplane(this.id) == true) {
                //Success update
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.SUCCESS, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("deleteSuccessAirplaneMessage")
                );
            }
            else {
                //Error creating
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("deleteDangerAirplaneMessage")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/airplane/index?faces-redirect=true";
    }
}
