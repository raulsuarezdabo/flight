/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.jsf.user;

import com.raulsuarezdabo.flight.service.UserService;
import com.raulsuarezdabo.flight.entity.UserEntity;
import com.raulsuarezdabo.flight.jsf.message.Message;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Bean for managing the recovery password action
 *
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class userRecoveryPasswordBean {
    
    /**
     * email of the user
     */
    private String email;
    
    /**
     * token of the user
     */
    private String token;

    /**
     * Password to type
     */
    private String passwordOne;

    /**
     * Re-type password
     */
    private String passwordTwo;
    
    /**
     * User service to use on the view
     */
    @Autowired
    @ManagedProperty(value="#{userService}")
    private UserService userService;

    /**
     * Creates a new instance of userRecoveryPasswordBean
     */
    public userRecoveryPasswordBean() {
    }

    /**
     * Getter passwordOne
     *
     * @return String passwordOne
     */
    public String getPasswordOne() {
        return passwordOne;
    }

    /**
     * Setter password one
     *
     * @param passwordOne String
     */
    public void setPasswordOne(String passwordOne) {
        this.passwordOne = passwordOne;
        if (this.passwordTwo != null) {
            if (this.passwordOne.compareTo(passwordTwo) != 0) {
                this.passwordOne = null;
                String errorMessage = FacesContext.getCurrentInstance().getApplication().
                        getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("passwordInvalid");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("userForm:passwordOne", message);
            }
        }
    }

    /**
     * Getter password two
     *
     * @return String passwordTwo
     */
    public String getPasswordTwo() {
        return passwordTwo;
    }

    /**
     * Setter passwordTwo
     *
     * @param passwordTwo String
     */
    public void setPasswordTwo(String passwordTwo) {
        this.passwordTwo = passwordTwo;
        if (this.passwordOne != null) {
            if (this.passwordOne.compareTo(passwordTwo) != 0) {
                this.passwordOne = null;
                String errorMessage = FacesContext.getCurrentInstance().getApplication().
                        getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString("passwordInvalid");
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        errorMessage, errorMessage);
                FacesContext.getCurrentInstance().addMessage("userForm:passwordTwo", message);
            }
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
        this.email = email;
    }

    /**
     * Getter of token
     * @return  String
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter of the token
     * @param token     String
     */
    public void setToken(String token) {
        this.token = token;
    }
    
    @PostConstruct
    public void init() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.email = parameterMap.get("email");
        this.token = parameterMap.get("token");
        try {
            UserEntity user = this.userService.getByEmail(URLDecoder.decode(email, "UTF-8"));
            if (user.getToken().compareTo(token) != 0) {
                this.email = null;
            }
        } catch(UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Method that updates the password of the user
     * @return 
     */
    public String recoveryPasswordAction() throws Exception {
        if (this.passwordOne != null && this.passwordTwo != null && this.passwordOne.compareTo(this.passwordTwo) == 0) {
            
            UserEntity userEdit = new UserEntity();
            userEdit.setPassword(passwordOne);
            userEdit.setToken("");
            if (this.userService.updateUser(this.email, userEdit, true) != null) {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.SUCCESS, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("editSuccessPasswordMessage")
                );
            }
            else {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER, 
                    FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msg").getString("editDangerPasswordMessage")
                );
            }
            return "/register/login?faces-redirect=true";
        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER, 
            FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                FacesContext.getCurrentInstance(), "msg").getString("editDangerPasswordMessage")
        );
        return "/register/recovery-password?faces-redirect=true&email=" + URLEncoder.encode(this.email, "UTF8") + "&token=" + this.token;
    }

}
