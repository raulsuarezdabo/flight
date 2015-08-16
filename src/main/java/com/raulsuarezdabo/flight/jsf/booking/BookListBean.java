/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.jsf.booking;

import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.service.BookService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author raulsuarez
 */
@ManagedBean
@ViewScoped
public class BookListBean {
    
    /**
     * BookService
     */
    @Autowired
    @ManagedProperty(value = "#{bookService}")
    private BookService bookService;
    
    private List<BookEntity> books;

    /**
     * Creates a new instance of BookListBean
     */
    public BookListBean() {
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
     * Getter books
     * @return  List of books
     */
    public List<BookEntity> getBooks() {
        return books;
    }

    /**
     * Setter books
     * @param books List 
     */
    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
    
    @PostConstruct
    public void init() {
        this.books = this.bookService.getAll();
    }
    
}
