/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.jsf.error;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author raulsuarez
 */
@ManagedBean(eager=true)
@RequestScoped
public class ErrorHandler {

    public String getStatusCode() {
        String val = String.valueOf((Integer) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.status_code"));
        return val;
    }

    public String getMessage() {
        String val = (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.message");
        return val;
    }

    public String getExceptionType() {
        String val = FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.exception_type").toString();
        return val;
    }

    public String getException() {
        String val = (String) ((Exception) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.exception")).toString();
        return val;
    }

    public String getRequestURI() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.request_uri");
    }

    public String getServletName() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.servlet_name");
    }

    public String getStackTrace() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        Throwable ex = (Throwable) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("javax.servlet.error.exception");
        ex.printStackTrace(pw);
        
        return pw.toString();
    }

}
