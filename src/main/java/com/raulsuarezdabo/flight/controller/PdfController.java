
package com.raulsuarezdabo.flight.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author raulsuarez
 */
@Controller
public class PdfController {
    
    public PdfController() {
    }
    
    @RequestMapping(value = "/book{id}.pdf", method={RequestMethod.GET})
    @ResponseBody
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id)
            throws ServletException, IOException {

        ModelAndView mv = new ModelAndView("pdf.jsp");
        mv.addObject("id", id);
        return mv;
    }
     
}
