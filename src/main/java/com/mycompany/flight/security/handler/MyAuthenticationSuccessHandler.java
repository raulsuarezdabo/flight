/**
 * Handler that choose where is the correct redirection after the login by
 * Spring Security
 *
 * @author raulsuarez
 */
package com.mycompany.flight.security.handler;

import com.mycompany.flight.utils.SessionConstantsName;
import com.raulsuarezdabo.flight.entity.RoleEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws IOException, ServletException {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey(SessionConstantsName.BOOKINGSEARCH) == true) {
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/booking-process/seats.xhtml?faces-redirect=true");
        } else {
            boolean isAdmin = false;
            for (GrantedAuthority role: a.getAuthorities()) {
                if (role.getAuthority().equals(RoleEntity.ADMIN_ROLE) == true) {
                    isAdmin = true;
                }
            }
            if (isAdmin == true) {
                response.sendRedirect(request.getContextPath() + "/dashboard/index.xhtml");   
            }
            else {
                UserEntity user = (UserEntity) a.getPrincipal();
                response.sendRedirect(request.getContextPath() + "/index.xhtml");   
            }
        }
    }
}
