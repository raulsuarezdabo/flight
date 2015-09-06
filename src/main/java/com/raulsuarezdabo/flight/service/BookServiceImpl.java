
package com.raulsuarezdabo.flight.service;

import com.mycompany.flight.service.EmailService;
import com.mycompany.flight.utils.Utils;
import com.raulsuarezdabo.flight.dao.BookDAO;
import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to manage book layer logic
 * @author raulsuarez
 */
@Service
public class BookServiceImpl implements BookService {
    
    public static int DAYS = 60;
    
    @Autowired
    private FlightService flightService;
    
    @Autowired
    private BookDAO bookDAO;
    
    @Autowired
    private EmailService emailService;

    /**
     * Method to add his book
     * @param user  UserEntity
     * @param flight    FlightEntity
     * @param seats Set
     * @return  BookEntity
     */
    @Override
    @Transactional
    public BookEntity addBook(UserEntity user, FlightEntity flight, Set<SeatEntity> seats, Locale locale) {
        try {
            if (this.flightService.checkAvaliability(flight, seats) == false) {
                throw new Exception("Not available seats");
            }
            BookEntity book = new BookEntity();
            book.setFlight(flight);
            book.setUser(user);
            book.setStatus(BookEntity.PENDING);
            this.bookDAO.addBook(book);
            if (this.flightService.addSeats(flight, seats, book) == false) {
                throw new Exception("Error! Imposible to add this seats");
            }
            ArrayList to = new ArrayList();
            to.add(user);
            this.emailService.sendMail(to, this.prepareInfoForemail("booking_confirmation", locale, user, book), "booking_confirmation", locale);
            return book;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * method for update book to confirm
     * @param book  BookEntity
     * @return  boolean
     */
    @Override
    @Transactional
    public BookEntity confirmBook(BookEntity book, Locale local) {
        try {
            book.setStatus(BookEntity.CONFIRM);
            if (this.bookDAO.updateBook(book) == true) {
                ArrayList to = new ArrayList();
                to.add(book.getUser());
                this.emailService.sendMail(to, this.prepareInfoForemail("booking_confirmed", local, book.getUser(), book), "booking_confirmed", local);
                return this.bookDAO.findById(book.getId());
            }
            else {
                return null;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Method for adding more than one book proces
     * @param user  UserEntity
     * @param flights   List of flights
     * @param seats Set of seats
     * @return  List of books
     */
    @Override
    @Transactional
    public List <BookEntity> addBook(UserEntity user, List<FlightEntity> flights, Set <SeatEntity> seats, Locale locale) {
        try {
            List<BookEntity> books = new ArrayList();
            for (FlightEntity fligh: flights) {
                BookEntity temporalBook = this.addBook(user, fligh, seats, locale);
                if (temporalBook == null) {
                    throw new Exception("Book transactional list fails");
                }
                books.add(temporalBook);
            }
            return books;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        } 
    }
    
    /**
     * Method to get books from this user
     * @param user  UserEntity
     * @return  List of books
     */
    @Override
    @Transactional
    public List<BookEntity> getBooks(UserEntity user) {
        return this.bookDAO.findByUser(user);
    }
    
    /**
     * Method to get books from this flight
     * @param flight    FlightEntity
     * @return  List of books
     */
    @Override
    @Transactional
    public List<BookEntity> getBooks(FlightEntity flight) {
        return this.bookDAO.findByFlight(flight);
    }
    
    /**
     * Method for returning a report for chart
     * @return  List
     */
    @Override
    public List getChart() {
        try {
            return this.bookDAO.findCountBooksByDate(BookServiceImpl.DAYS);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Get all books available
     * @return List all books listed
     */
    @Override
    public List<BookEntity> getAll() {
        try {
            List<BookEntity> books = this.bookDAO.findAll();
            return books;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Find book by id
     * @param id    int
     * @return  BookEntity
     */
    @Override
    public BookEntity getById(int id) {
        try {
            return this.bookDAO.findById(id);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    /**
     * Method that prepares the information for the email
     *
     * @param wellcome type of e-mail
     * @param locale Location of the email (language)
     * @param user User model where comes the information
     * @param book BookEntity refered to send by email
     * @return HasMap
     */
    private HashMap prepareInfoForemail(String type, Locale locale, UserEntity user, BookEntity book) {
        try {
            String messages = null;
            if (locale.getLanguage().equals("es")) {
                messages = "com.mycompany.flight.messages";
            } else {
                messages = "com.mycompany.flight.messages_" + locale.getLanguage().toLowerCase();
            }
            ResourceBundle resource = ResourceBundle.getBundle(messages);
            HashMap map = new HashMap();
            map.put("wellcome", MessageFormat.format(resource.getString("wellcomeEmail"), user.getName()));
            if (type.compareTo("booking_confirmation") == 0) {
                map.put("title", resource.getString("bookingConfirmationEmailTitle"));
                map.put("bookText", MessageFormat.format(resource.getString("bookingConfirmationText"), book.getFlight().getAirportFrom().getCity().getName(), book.getFlight().getAirportTo().getCity().getName(), new SimpleDateFormat("MM-dd-yyyy").format(book.getFlight().getStart())));
                map.put("bookingConfirmationLink", "booking-confirmation/index.xhtml?parameter=" + book.getId());
                map.put("confirm", resource.getString("confirm"));
            }
            if (type.compareTo("booking_confirmed") == 0) {
                map.put("title", resource.getString("bookingConfirmedEmailTitle"));
                map.put("bookText", MessageFormat.format(resource.getString("bookingConfirmedText"), book.getFlight().getAirportFrom().getCity().getName(), book.getFlight().getAirportTo().getCity().getName(), new SimpleDateFormat("MM-dd-yyyy").format(book.getFlight().getStart())));
                map.put("bookLink", Utils.getUrl("book" + book.getId() + ".pdf", null));
                map.put("printPdf", resource.getString("printPdf"));
            }
            return map;
        } catch (Exception e) {
            return null;
        }
    }
    
}
