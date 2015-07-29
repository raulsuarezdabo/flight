package com.raulsuarezdabo.flight.jsf.user;

import com.raulsuarezdabo.flight.entity.UserEntity;
import com.mycompany.flight.service.UserService;
import com.raulsuarezdabo.flight.entity.RoleEntity;
import com.raulsuarezdabo.flight.jsf.message.Message;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
@ManagedBean(eager = true)
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

    /**
     * token of the user, autologin in two steps
     */
    private String token;

    @Autowired
    @ManagedProperty(value = "#{userService}")
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
     * Getter token
     * @return  String
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter token
     * @param token String 
     */
    public void setToken(String token) {
        this.token = token;
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
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (requestParameters.containsKey("token") == true) {
            this.token = requestParameters.get("token");
            //Hook for new user, second step and autologin
            UserEntity user = this.userService.getByEmail(this.email);
            if (user == null || user instanceof UserEntity == false || user.getToken().compareTo(this.token) != 0) {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.DANGER,
                        FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                                FacesContext.getCurrentInstance(), "msg").getString("passwordFailsUserMessage")
                );
                return "/register/forgot-account?faces-redirect=true";
            }
            UserEntity userEdit = new UserEntity();
            userEdit.setPassword(this.password);
            userEdit.setToken("");
            if (this.userService.updateUser(this.email, userEdit, true) == null) {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put(Message.WARNING,
                        FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                                FacesContext.getCurrentInstance(), "msg").getString("passwordFailsUserMessage")
                );
                return "/register/forgot-account?faces-redirect=true";
            }
        }
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
            } else {
//                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("param1", "logoutErrorMessage");
            }
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
        } catch (IOException ex) {
            Logger.getLogger(UserLoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isLogged() {
        try {
            return this.userService.isLogged();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
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
    
    /**
     * Method to get user roles
     * @return  List of roles
     */
    public List<RoleEntity> getLoggedUserRole() {
        return this.getLoggedUser().getRole();
    }
    
    /**
     * Method to know if a user is admin or not
     * @param user  UserEntity
     * @return  boolean
     */
    public boolean isAdmin(UserEntity user) {
        List <RoleEntity> roles = this.getLoggedUserRole();
        for (RoleEntity role: roles) {
            if (role.getName().equals(RoleEntity.ADMIN_ROLE)) {
                return true;
            }
        }
        return false;
    }

    @PostConstruct
    public void init() {

        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (parameterMap.containsKey("email") == true && parameterMap.containsKey("token") == true) {
            try {
                this.email = URLDecoder.decode(parameterMap.get("email"), "UTF-8");
                this.token = parameterMap.get("token");
            } catch (UnsupportedEncodingException e) {
                System.out.println(e.getMessage());
                this.token = null;
            }
        }

    }
}
