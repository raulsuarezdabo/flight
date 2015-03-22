
package com.mycompany.flight.service;

import com.raulsuarezdabo.flight.entity.UserEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 *
 * @author raulsuarez
 */
public interface EmailService {
    
    public void sendMail(ArrayList<UserEntity> receiver, HashMap content, String type, Locale locale);
}
