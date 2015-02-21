package com.raulsuarezdabo.flight.jsf.user;

import com.mycompany.flight.dao.UserDAO;
import com.raulsuarezdabo.flight.jsf.language.LocaleBean;
import com.mycompany.flight.service.UserService;
import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Bean to manage the forgotten user account password, it sends an e-mail with
 * the information to create a new one
 *
 * @author raulsuarez
 */
@ManagedBean(eager=true)
@ViewScoped
public class ForgotUserBean implements Serializable {

    /**
     * email of the user
     */
    private String email;
    
    /**
     * User service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{userService}")
    private UserService userService;

    /**
     * Getter of the user
     *
     * @return email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of the email
     *
     * @param email email of the user
     */
    public void setEmail(String email) {
        if (this.userService.getByEmail(email) == null) {
            // Bring the error message using the Faces Context
            String errorMessage = FacesContext.getCurrentInstance().getApplication().
                getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("emailNoExist");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("userForm:userEmail", message);
            this.email = email;
            
        } else {
            this.email = email;
        }
    }
    
    /**
     * userSErvice Getter
     *
     * @return
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * userService Setter
     *
     * @param userService
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * Method that creates the new user
     *
     * @return
     */
    public Boolean forgotAccountAction() {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext extCtx = ctx.getExternalContext();
            Map<String, Object> sessionMap = extCtx.getSessionMap();
            LocaleBean locale = (LocaleBean) sessionMap.get("localeBean");

            if (this.userService.forgotAccount(this.email,locale.getCurrent())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
