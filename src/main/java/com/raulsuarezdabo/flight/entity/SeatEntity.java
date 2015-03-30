
package com.raulsuarezdabo.flight.entity;

import java.sql.SQLException;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Seat entity that defines a seat on a specific flight
 * @author raulsuarez
 */
@Table(name = "Seat")
public class SeatEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    
    @Column(name = "type", nullable = false)
    private int type;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Flight")
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
     * Getter flight
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
