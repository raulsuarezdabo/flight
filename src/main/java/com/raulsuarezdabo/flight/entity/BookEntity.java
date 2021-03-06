/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.entity;

import com.raulsuarezdabo.flight.entity.listener.BookListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 *
 * @author raulsuarez
 */
@Entity
@EntityListeners({BookListener.class})
@Indexed
@Table(name = "Book")
public class BookEntity {
    private static final long serialVersionUID = 1112143564556L;
    
    public static final int CONFIRM = 1;
    public static final int PENDING = 0;
    
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "FlightID")
    private FlightEntity flight;
    
    @ManyToOne
    @JoinColumn(name = "UserID")
    private UserEntity user;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt", nullable = false)
    private Date createdAt;
    
    @Column(name = "Status")
    private Integer status;
    
    @IndexedEmbedded
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private Set<SeatEntity> seats = new HashSet<>();
    
    /**
     * Getter id property
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter id property
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Getter fligth
     * @return  FlightEntity
     */
    public FlightEntity getFlight() {
        return flight;
    }

    /**
     * Setter flight
     * @param flight    FlightEntity
     */
    public void setFlight(FlightEntity flight) {
        this.flight = flight;
    }

    /**
     * Getter createdAt
     * @return  Date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Setter createdAt
     * @param createdAt Date 
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Getter user
     * @return  UserEntity
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Setter user
     * @param user  UserEntity 
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Getter status
     * @return 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Setter Status
     * @param status    int 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    /**
     * Method that returns the name of the status
     * @return  String
     */
    public String getNameStatus() {
        if (this.status.equals(BookEntity.CONFIRM) == true) {
            return "CONFIRM";
        }
        if (this.status.equals(BookEntity.PENDING) == true) {
            return "PEDING";
        }
        return "UNDEFINED";
    }
    
    /**
     * Getter Seat set
     * @return Seat of seats
     */
    public Set<SeatEntity> getSeats() {
        return seats;
    }
    
    /**
     * Gets a list of seats
     * @return  List
     */
    public List<SeatEntity> getSeatsList() {
        if (this.seats.isEmpty() == false) {
            return new ArrayList<>(this.seats);
        }
        else {
            return new ArrayList<>();
        }
    }

    /**
     * Setter seatss
     * @param seats     Set
     */
    public void setSeats(Set<SeatEntity> seats) {
        this.seats = seats;
    }
    
}
