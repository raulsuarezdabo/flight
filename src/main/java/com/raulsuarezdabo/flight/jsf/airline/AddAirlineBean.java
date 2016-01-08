/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.jsf.airline;

import com.raulsuarezdabo.flight.utils.Utils;
import com.raulsuarezdabo.flight.entity.AirlineEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import com.raulsuarezdabo.flight.jsf.message.Message;
import com.raulsuarezdabo.flight.service.AirlineService;
import com.raulsuarezdabo.flight.service.CountryService;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class AddAirlineBean {

    /**
     * Creates a new instance of AddAirlineBean
     */
    public AddAirlineBean() {
    }
    
    /**
     * airline name
     */
    private String name;
    
    /**
     * airline code
     */
    private String code;
    
    /**
     * country of the airline
     */
    private String country;

    
    
    /**
     * Country service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{countryService}")
    private CountryService countryService;
    
    /**
     * Airline service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{airlineService}")
    private AirlineService airlineService;
    
    /**
     * List of countries
     */
    private List<CountryEntity> countries;
    
    /**
     * Getter of name
     *
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     *
     * @param name name of the user
     */
    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    /**
     * Getter code
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter code
     * @param code  String 
     */
    public void setCode(String code) {
        if (this.airlineService.getByCode(code) == null) {
            this.code = code;
        }
        else {
            // Bring the error message using the Faces Context
            String errorMessage = FacesContext.getCurrentInstance().getApplication().
                getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("codeExist");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("airlineForm:airlineCode", message);
            this.code = null;
        }
    }
    
    /**
     * Getter country
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter country
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * Getter list of countries
     *
     * @return
     */
    public List<CountryEntity> getCountries() {
        if (this.countries == null) {
            this.countries = this.countryService.getAll();
        }
        return this.countries;
    }

    /**
     * Setter countries
     *
     * @param countries
     */
    public void setCountries(List<CountryEntity> countries) {
        this.countries = countries;
    }
    
    /**
     * Getter countryService
     * @return 
     */
    public CountryService getCountryService() {
        return countryService;
    }

    /**
     * Setter of the countryService
     * @param countryService 
     */
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Getter airlineService
     * @return  AirlineService
     */
    public AirlineService getAirlineService() {
        return airlineService;
    }

    /**
     * Setter airlineService
     * @param airlineService    AirlineService
     */
    public void setAirlineService(AirlineService airlineService) {
        this.airlineService = airlineService;
    }
    
    public String addAirlineAction() {
        try {
            AirlineEntity airline = 
                this.airlineService.addAirline(
                    this.name, 
                    this.code, 
                    Utils.getCountryFromList(this.country, this.countries)
            );
            
            if (airline != null) {
                //Success update
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.SUCCESS, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("addSuccessAirlineMessage")
                );
            }
            else {
                //Error creating
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("addDangerAirlineMessage")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "/airline/index?faces-redirect=true";
    }
}
