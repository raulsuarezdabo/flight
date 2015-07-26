package com.raulsuarezdabo.flight.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author raulsuarez
 */
@Entity
@Indexed
@Table(name = "Book")
public class BookEntity {
    public static final int CONFIM = 1;
    
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
        if (this.status.equals(BookEntity.CONFIM) == true) {
            return "CONFIRM";
        }
        return "UNDEFINED";
    }
    
}
