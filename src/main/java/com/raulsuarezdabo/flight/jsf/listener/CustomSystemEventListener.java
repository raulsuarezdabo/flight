package com.raulsuarezdabo.flight.jsf.listener;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PreRenderViewEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

/**
 *
 * @author raulsuarez
 */
public class CustomSystemEventListener implements SystemEventListener {

    /**
     * Listener that performs some view custom settings before render the view.
     * @param event     PreRenderViewEvent
     * @throws AbortProcessingException 
     */
    @Override
    public void processEvent(SystemEvent event) throws AbortProcessingException {
        if (event instanceof PreRenderViewEvent) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            if ("/index.xhtml".equals(FacesContext.getCurrentInstance().getViewRoot().getViewId())) {
                if (externalContext.getSessionMap().containsKey("selectedFlightGo")) {
                    externalContext.getSessionMap().remove("selectedFlightGo");
                }
                if (externalContext.getSessionMap().containsKey("selectedFlightBack")) {
                    externalContext.getSessionMap().remove("selectedFlightBack");
                }
            }
        }
    }

    /**
     * Method that returns always true because we want to intercept all renders evers.
     * @param source    Object
     * @return  boolean
     */
    @Override
    public boolean isListenerForSource(Object source) {
        return true;
    }

}
