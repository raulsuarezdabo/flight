/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.jsf.validator;

import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

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
