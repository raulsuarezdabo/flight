/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.jsf.validator;

import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author raulsuarez
 */
public class AgeValidator implements Validator {
    // minimal age to be a user
    public static int MINIMAL_AGE = 18;
    
    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        Date birthDay = (Date) o;
        Calendar cal = Calendar.getInstance();
        // substract 18 years to be more than 18 years old the birdthday as de minimal age
        cal.add(Calendar.YEAR, - AgeValidator.MINIMAL_AGE);
        Date minimal = cal.getTime();
        if (minimal.compareTo(birthDay) < 0 ){
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
    
}
