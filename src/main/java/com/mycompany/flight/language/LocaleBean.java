package com.mycompany.flight.language;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author raulsuarez
 */
@Named(value = "localeBean")
@SessionScoped
public class LocaleBean implements Serializable {

    private HashMap<String, Locale> locales = null;

    /**
     * Default constructor
     */
    public LocaleBean() {
        locales = new HashMap<>(2);
        locales.put("EN", new Locale("en", "EN"));
        locales.put("ES", new Locale("es", "ES"));
    }

    /**
     * Switch location to new one
     * @param local new location to apply
     */
    public void chooseLocaleFromLink(String local) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getViewRoot().setLocale((Locale) locales.get(local));
    }
}
