
package com.raulsuarezdabo.flight.entity;

import java.sql.SQLException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.search.annotations.Indexed;

/**
 * Seat entity that defines a seat on a specific flight
 * @author raulsuarez
 */
@Entity
@Indexed
@Table(name = "Seat")
public class SeatEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    
    @Column(name = "Type", nullable = false)
    private int type;
    
    @Column(name = "Fullname")
    private String fullName;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FlightID")
    private FlightEntity flight;

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
     * Getter type 
     * @return int
     */
    public int getType() {
        return type;
    }

    /**
     * Setter typer
     * @param type  int
     */
    public void setType(int type) {
        if (type != ClassEntity.TOURIST && type != ClassEntity.OFFER && type != ClassEntity.BUSINESS) {
            throw new ConstraintViolationException("Violation exception on type", new SQLException(), "Constraint violation seat type");
        }
        this.type = type;
    }

    /**
     * Getter fullName
     * @return  String
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Setter fullName
     * @param fullName  String 
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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

}
