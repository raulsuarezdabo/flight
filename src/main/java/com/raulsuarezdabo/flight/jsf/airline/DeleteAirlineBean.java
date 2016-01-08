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

import com.raulsuarezdabo.flight.jsf.airline.*;
import com.raulsuarezdabo.flight.utils.Utils;
import com.raulsuarezdabo.flight.entity.AirlineEntity;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import com.raulsuarezdabo.flight.jsf.message.Message;
import com.raulsuarezdabo.flight.service.AirlineService;
import com.raulsuarezdabo.flight.service.CityService;
import com.raulsuarezdabo.flight.service.CountryService;
import static java.lang.Integer.parseInt;
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
public class DeleteAirlineBean {

    /**
     * Creates a new instance of AddAirlineBean
     */
    public DeleteAirlineBean() {
    }
    
    /**
     * id int
     */
    private int id;
    
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
        this.code = code;
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
    
    @PostConstruct
    public void init() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.id = parseInt(parameterMap.get("parameter"));
        AirlineEntity airline = this.airlineService.getById(this.id);
        if (airline != null && (airline instanceof AirlineEntity) == true) {
            this.name = airline.getName();
            this.code = airline.getCode();
            this.country = airline.getCountry().getCode();
        }
    }
    
    /**
     * Method that updates the selected airline
     * @return String   target url
     */
    public String deleteAirlineAction () {
        if (this.airlineService.deleteAirline(this.id) == true ) {
            //Error on persisting the user
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.SUCCESS, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("deleteSuccessAirlineMessage")
                );
        }
        else {
            //If not error is updated
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("deleteDangerAirlineMessage")
                );
        }
        return "/airline/index?faces-redirect=true";
    }
}
