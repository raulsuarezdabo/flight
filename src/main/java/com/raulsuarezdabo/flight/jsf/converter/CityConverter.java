
package com.raulsuarezdabo.flight.jsf.converter;

import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.service.CityService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Converter of citieEntity for conversion standard
 * @author raulsuarez
 */
@ManagedBean
@RequestScoped
public class CityConverter implements Converter {
    
    /**
     * city service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{cityService}")
    private CityService cityService;
    
    /**
     * Getter of the service city
     *
     * @return CityService
     */
    public CityService getCityService() {
        return cityService;
    }

    /**
     * Setter of the service city
     *
     * @param cityService
     */
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Conversor for city as Geeting as object
     * @param fc
     * @param uic
     * @param string
     * @return 
     */
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && string.trim().length()> 0) {
            try {
                return this.cityService.getById((Integer.parseInt(string)));
            } catch (Exception e) {
                String errorMessage = FacesContext.getCurrentInstance().getApplication().
                        getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("cityInvalid");
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", errorMessage));
            }
        }
        else {
            return null;
        }
    }

    /**
     * Converter as string
     * @param fc
     * @param uic
     * @param o
     * @return 
     */
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return String.valueOf(((CityEntity) o).getId());
        }
        else {
            return null;
        }
    }
    
}
