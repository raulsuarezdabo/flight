package com.mycompany.flight.language;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import javax.faces.context.FacesContext;

/**
 *
 * @author raulsuarez
 */
public class LocaleBean implements Serializable {

    /**
     * Avaliable locations
     */
    private HashMap<String, Locale> locales = null;
    private Locale current;

    /**
     * Default constructor
     */
    public LocaleBean() {
        this.current = null;
        current = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        locales = new HashMap<>(2);
        locales.put("EN", new Locale("EN", "EN"));
        locales.put("ES", new Locale("ES", "ES"));
    }

    /**
     * Switch location to new one
     * @param local new location to apply
     */
    public void chooseLocaleFromLink(String local) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale((Locale) locales.get(local));
        this.current = context.getViewRoot().getLocale();
    }

    /**
     * Getter of the current language selected
     * @return 
     */
    public Locale getCurrent() {
        return current;
    }

    /**
     * Setter of the current language selected
     * @param current 
     */
    public void setCurrent(Locale current) {
        this.current = current;
    }
    
    
    
}
