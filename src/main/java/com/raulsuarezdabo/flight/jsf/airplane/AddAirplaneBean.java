package com.raulsuarezdabo.flight.jsf.airplane;

import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import com.raulsuarezdabo.flight.jsf.message.Message;
import com.raulsuarezdabo.flight.service.AirplaneService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
public class AddAirplaneBean {

    /**
     * Airline service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{airplaneService}")
    private AirplaneService airplaneService;

    /**
     * Creates a new instance of AddAirplane
     */
    public AddAirplaneBean() {
    }

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
        this.checkNumberSeats();
        this.coherenceNumberSeats();
    }

    /**
     * Method to check if is missing required values
     */
    private void checkNumberSeats() {
        if (this.numSeatsTourist == null) {
            String errorMessage = FacesContext.getCurrentInstance().getApplication().
                    getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("numSeatsTouristRequired");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("airplaneForm:airplaneNumSeatsTourist", message);
        }
        if (this.numSeatsOffer == null) {
            String errorMessage = FacesContext.getCurrentInstance().getApplication().
                    getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("numSeatsOfferRequired");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("airplaneForm:airplaneNumSeatsOffer", message);
        }
        if (this.numSeatsBusiness == null) {
            String errorMessage = FacesContext.getCurrentInstance().getApplication().
                    getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("numSeatsBusinessRequired");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("airplaneForm:airplaneNumSeatsBusiness", message);
        }
    }

    /**
     * Method to check if it's correct or not
     * @return  boolean coherence or not 
     */
    private void coherenceNumberSeats() {
        if (this.numSeatsBusiness != null && this.numSeatsOffer != null && this.numSeatsTourist != null) {
            if (((this.numSeatsBusiness + this.numSeatsOffer + this.numSeatsTourist) == this.numSeatsTotal) == false) {
                String errorMessage = FacesContext.getCurrentInstance().getApplication().
                        getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("numSeatsTotalNotMatch");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("airplaneForm:airplaneNumSeatsBusiness", message);
            }
        }
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
        this.checkNumberSeats();
        this.coherenceNumberSeats();
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
        this.checkNumberSeats();
        this.coherenceNumberSeats();
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
        this.checkNumberSeats();
        this.coherenceNumberSeats();
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
    }
    /**
     * Action method for adding new airplane to the system
     * @return  String  with the redirection to main page
     */
    public String addAirplaneAction() {
        try {
            AirplaneEntity airplane = 
                this.airplaneService.addAirplane(
                        this.model, 
                        this.maker, 
                        this.year, 
                        this.numSeatsTotal, 
                        this.numSeatsOffer, 
                        this.numSeatsTourist, 
                        this.numSeatsBusiness
                );
            if (airplane != null) {
                //Success update
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.SUCCESS, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("addSuccessAirplaneMessage")
                );
            }
            else {
                //Error creating
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("addDangerAirplaneMessage")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/airplane/index?faces-redirect=true";
    }
}
