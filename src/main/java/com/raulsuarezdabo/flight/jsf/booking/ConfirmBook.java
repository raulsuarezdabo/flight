package com.raulsuarezdabo.flight.jsf.booking;

import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.service.BookService;
import static java.lang.Integer.parseInt;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
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
                this.book = this.bookService.confirmBook(book);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            this.book = null;
        }

    }

}
