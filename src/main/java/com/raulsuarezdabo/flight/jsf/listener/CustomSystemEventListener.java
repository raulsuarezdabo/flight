/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.jsf.listener;

import com.raulsuarezdabo.flight.utils.SessionConstantsName;
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
                if (externalContext.getSessionMap().containsKey(SessionConstantsName.BOOKINGSEARCH)) {
                    externalContext.getSessionMap().remove(SessionConstantsName.BOOKINGSEARCH);
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
