/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.service;

import com.mycompany.flight.entity.UserEntity;
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
