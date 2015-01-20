
package com.mycompany.flight.security;

import java.io.Serializable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author raulsuarez
 */
public class UserLoginBean implements Serializable {
    
    /**
     * email of the user
     */
    private String email;
    
    private String password;
    
    private AuthenticationManager authenticationManager;

    /**
     * Getter Email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter Email
     * @param email String 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter password
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter AuthentificationManager
     * @return authenticationManager
     */
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    /**
     * Setter AuthenticationManager
     * @param authenticationManager 
     */
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    public void checkAction() {
        System.out.println(this.email + this.password);
    }
    
    /**
     * Loggin method
     * @return 
     */
    public String login() {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(this.email, this.password);
            Authentication authenticate = this.authenticationManager.authenticate(token);

            if (authenticate.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            }
            return "success";
        } catch (Exception e) {
            return "incorrect";
        }
    }

    public String logout() {
        SecurityContextHolder.clearContext();
        return "loggedout";
    }
    
}
