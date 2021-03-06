/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.security.handler;

import com.raulsuarezdabo.flight.utils.SessionConstantsName;
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
            boolean isManager = false;
            for (GrantedAuthority role: a.getAuthorities()) {
                if (role.getAuthority().equals(RoleEntity.ADMIN_ROLE) == true) {
                    isAdmin = true;
                }
                if (role.getAuthority().equals(RoleEntity.MANAGER_ROLE) == true) {
                    isManager = true;
                }
            }
            if (isAdmin == true || isManager == true) {
                response.sendRedirect(request.getContextPath() + "/dashboard/index.xhtml");   
            }
            else {
                UserEntity user = (UserEntity) a.getPrincipal();
                response.sendRedirect(request.getContextPath() + "/index.xhtml");   
            }
        }
    }
}
