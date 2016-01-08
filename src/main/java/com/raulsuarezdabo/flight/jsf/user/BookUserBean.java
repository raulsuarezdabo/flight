/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.raulsuarezdabo.flight.jsf.user;

import com.mycompany.flight.service.UserService;
import com.raulsuarezdabo.flight.utils.Utils;
import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import com.raulsuarezdabo.flight.service.BookService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Managed bean for showing user books
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class BookUserBean {
    
    /**
     * BookService
     */
    @Autowired
    @ManagedProperty(value = "#{bookService}")
    private BookService bookService;
    
    /**
     * User service to use on the view
     */
    @Autowired
    @ManagedProperty(value = "#{userService}")
    private UserService userService;
    
    /**
     * List of user books
     */
    private List<BookEntity> books;
    
    /**
     * Getter of the userService
     *
     * @return
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Setter of the userService
     *
     * @param userService
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
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
     * Getter list of books
     * @return  List
     */
    public List<BookEntity> getBooks() {
        return books;
    }

    /**
     * Setter books list
     * @param books     List
     */
    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
    
    /**
     * Method to get url 
     * @param book  BookEntity
     * @return  String
     */
    public String getBookPdf(BookEntity book) {
        return Utils.getUrl("book" + book.getId() + ".pdf", null);
    }

    /**
     * Creates a new instance of BookUserBean
     */
    public BookUserBean() {
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
        UserEntity user = this.userService.getLoggedUser();
        this.books = this.bookService.getBooks(user);
    }
    
}
