/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.jsf.airport;

import com.raulsuarezdabo.flight.utils.Utils;
import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import com.raulsuarezdabo.flight.jsf.message.Message;
import com.raulsuarezdabo.flight.service.AirportService;
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
public class DeleteAirportBean {

    /**
     * Creates a new instance of AddAirportBean
     */
    public DeleteAirportBean() {
    }
    
    /**
     * id int
     */
    private int id;
    
    /**
     * airport name
     */
    private String name;
    
    /**
     * airport code
     */
    private String code;
    
    /**
     * country of the airport
     */
    private String country;

    /**
     * city of the airport
     */
    private int city;
    
    
    /**
     * Country service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{countryService}")
    private CountryService countryService;
    
    /**
     * City service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{cityService}")
    private CityService cityService;
    
    /**
     * Airport service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{airportService}")
    private AirportService airportService;
    
    /**
     * List of countries
     */
    private List<CountryEntity> countries;

    /**
     * List of cities
     */
    private List<CityEntity> cities;
    
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
     * Getter Cities
     *
     * @return
     */
    public List<CityEntity> getCities() {
        if (this.country != null) {
            this.cities = this.cityService.getCitiesByCountry(Utils.getCountryFromList(this.country, this.countries));
        } else {
            this.cities = null;
        }
        return this.cities;
    }

    /**
     * Setter of cities
     *
     * @param cities
     */
    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
    }
    
    /**
     * Getter city
     *
     * @return CityEntity
     */
    public int getCity() {
        return city;
    }

    /**
     * Setter city
     *
     * @param city
     */
    public void setCity(int city) {
        this.city = city;
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
     * Getter of the cityService
     * @return CityService
     */
    public CityService getCityService() {
        return cityService;
    }

    /**
     * Setter of the cityService
     * @param cityService CityService
     */
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Getter airportService
     * @return  AirportService
     */
    public AirportService getAirportService() {
        return airportService;
    }

    /**
     * Setter airportService
     * @param airportService    AirportService
     */
    public void setAirportService(AirportService airportService) {
        this.airportService = airportService;
    }
    
    @PostConstruct
    public void init() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.id = parseInt(parameterMap.get("parameter"));
        AirportEntity airport = this.airportService.getById(this.id);
        if (airport != null && (airport instanceof AirportEntity) == true) {
            this.name = airport.getName();
            this.code = airport.getCode();
            this.country = airport.getCountry().getCode();
            this.city = airport.getCity().getId();
        }
    }
    
    /**
     * Method that updates the selected airport
     * @return String   target url
     */
    public String deleteAirportAction () {
        if (this.airportService.deleteAirport(this.id) == true ) {
            //If not error is updated
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.SUCCESS, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("deleteSuccessAirportMessage")
                );
        }
        else {
            //Error on persisting the user
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("deleteDangerAirportMessage")
                );
        }
        return "/airport/index?faces-redirect=true";
    }
}
