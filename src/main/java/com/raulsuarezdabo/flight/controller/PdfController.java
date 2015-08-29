
package com.raulsuarezdabo.flight.controller;

import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.service.BookService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private BookService bookService;

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    
    public PdfController() {
    }
    
    @RequestMapping(value = "/book{id}.pdf", method={RequestMethod.GET})
    @ResponseBody
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id)
            throws ServletException, IOException, Exception {
        BookEntity book = this.bookService.getById(id);
        if (book == null) {
            throw new Exception("Book not found with id: " + id);
        }
        return new ModelAndView(
            "pdfBuilder", 
            "book", 
            this.bookService.getById(id)
        );
    }
     
}
