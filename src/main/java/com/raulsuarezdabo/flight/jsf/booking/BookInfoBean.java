/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.jsf.booking;

import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import com.raulsuarezdabo.flight.service.BookService;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class BookInfoBean {
    
    /**
     * BookService
     */
    @Autowired
    @ManagedProperty(value = "#{bookService}")
    private BookService bookService;
    
    private BookEntity book;
    
    /**
     * Getter bookService
     *
     * @return BookService
     */
    public BookService getBookService() {
        return bookService;
    }

    /**
     * Setter bookService
     *
     * @param bookService BookService
     */
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Getter book
     * @return  BookEntity
     */
    public BookEntity getBook() {
        return book;
    }

    /**
     * Setter book
     * @param book  BookEntity 
     */
    public void setBook(BookEntity book) {
        this.book = book;
    }
    
    /**
     * Creates a new instance of BookInfoBean
     */
    public BookInfoBean() {
    }
    
    /**
     * Sum initial date to time of flight
     *
     * @param init Date
     * @param time Date
     * @return Date
     */
    public Date flightFinish(Date init, Date time) {
        if (init != null && time != null) {
            return new Date(init.getTime() + time.getTime());
        }
        return null;
    }
    
    @PostConstruct
    public void init() {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer id = (Integer) parseInt(parameterMap.get("parameter"));
        this.book = this.bookService.getById(id);
    }
}
