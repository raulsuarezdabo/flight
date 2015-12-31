/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.mycompany.flight.service;

import com.raulsuarezdabo.flight.entity.UserEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public interface EmailService {
    
    public void sendMail(ArrayList<UserEntity> receiver, HashMap content, String type, Locale locale);
}
