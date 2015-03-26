/**
 * Handler that choose where is the correct redirection after the login by
 * Spring Security
 *
 * @author raulsuarez
 */
package com.mycompany.flight.security.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws IOException, ServletException {
//        try {
//            UserEntity user = (UserEntity) a.getPrincipal();
//            response.sendRedirect(request.getContextPath() + "/index.xhtml");
//        } catch (Exception e) {
//            response.sendRedirect(request.getContextPath() + "/index.xhtml");
//        }
    }
}
