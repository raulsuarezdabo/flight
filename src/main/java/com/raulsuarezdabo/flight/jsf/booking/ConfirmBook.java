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
import com.raulsuarezdabo.flight.jsf.language.LocaleBean;
import com.raulsuarezdabo.flight.service.BookService;
import static java.lang.Integer.parseInt;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Bean to confirm a book
 *
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class ConfirmBook {

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
     *
     * @return BookEntity
     */
    public BookEntity getBook() {
        return book;
    }

    /**
     * Setter book
     *
     * @param book BookEntity
     */
    public void setBook(BookEntity book) {
        this.book = book;
    }

    @PostConstruct
    public void init() {
        try {
            Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            Integer id = (Integer) parseInt(parameterMap.get("parameter"));
            this.book = this.bookService.getById(id);
            if (this.book instanceof BookEntity) {
                FacesContext ctx = FacesContext.getCurrentInstance();
                ExternalContext extCtx = ctx.getExternalContext();
                Map<String, Object> sessionMap = extCtx.getSessionMap();
                LocaleBean locale = (LocaleBean) sessionMap.get("localeBean");
                this.book = this.bookService.confirmBook(book, locale.getCurrent());
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            this.book = null;
        }

    }

}
