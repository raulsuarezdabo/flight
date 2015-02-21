package com.raulsuarezdabo.flight.jsf.user;

import com.mycompany.flight.entity.UserEntity;
import com.mycompany.flight.service.UserService;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author raulsuarez
 */
@ManagedBean(eager=true)
@RequestScoped
public class UserLoginBean implements Serializable {

    /**
     * email of the user
     */
    private String email;

    /**
     * password of the user
     */
    private String password;

    @Autowired
    @ManagedProperty(value="#{userService}")
    private UserService userService;

    /**
     * Getter Email
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter Email
     *
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter userService
     *
     * @return
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Setter userService
     *
     * @param userService
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Loggin method that checks if the login procces works ok
     *
     * @return
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public String login() throws ServletException, IOException {
        if (this.userService.checkCredentails(this.email, this.password) == true) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = ((HttpServletRequest) context.getRequest());

            ServletResponse response = ((ServletResponse) context.getResponse());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
            dispatcher.forward(request, response);
            FacesContext.getCurrentInstance().responseComplete();
        } else {
            String errorMessage = FacesContext.getCurrentInstance().getApplication().
                    getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("emailFails");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, errorMessage);
            FacesContext.getCurrentInstance().addMessage("j_username", message);
        }
        return null;
    }

    public void logout() {
        try {
            if (this.userService.logout() == true) {
//                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "logoutSuccessMessage");
            }
            else {
//                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("param1", "logoutErrorMessage");
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
        } catch (IOException ex) {
            Logger.getLogger(UserLoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isLogged() {
        return this.userService.isLogged();
    }

    /**
     * Method to access the entity UserEntity which collect the information
     * about the logged user
     *
     * @return
     */
    public UserEntity getLoggedUser() {
        return this.userService.getLoggedUser();
    }
}
