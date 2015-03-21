package com.raulsuarezdabo.flight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Temporal;

/**
 * Class for managing flights
 * @author raulsuarez
 */
@Entity
@Table(name = "User")
public class FlightEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    
    @Column(name = "Code", unique = true, nullable = false)
    private String code;
    
    @Column(name = "Status", nullable = false)
    private int status;
    
    @OneToOne
    @JoinColumn(name = "AirportFrom")
    private AirportEntity airportFrom;

    @OneToOne
    @JoinColumn(name = "AirportTo")
    private AirportEntity airportTo;
    
    @Column(name = "Start", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date start;
    
    @Column(name = "Ends", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ends;

    
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
     * Getter code
     * @return  String
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter code
     * @param code String
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter 
     * @return 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Setter Status
     * @param status    int 
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Getter airportFrom
     * @return  AirportEntity
     */
    public AirportEntity getAirportFrom() {
        return airportFrom;
    }

    /**
     * Setter airportEntity
     * @param airportFrom   AirportEntity 
     */
    public void setAirportFrom(AirportEntity airportFrom) {
        this.airportFrom = airportFrom;
    }

    /**
     * Getter airportEntity
     * @return  AirportEntity
     */
    public AirportEntity getAirportTo() {
        return airportTo;
    }

    /**
     * Setter airportEntity
     * @param airportTo     AirportEntity
     */
    public void setAirportTo(AirportEntity airportTo) {
        this.airportTo = airportTo;
    }

    /**
     * Getter Start
     * @return  Date
     */
    public Date getStart() {
        return start;
    }

    /**
     * Setter Start
     * @param start Date 
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * Getter Ends
     * @return  Date
     */
    public Date getEnds() {
        return ends;
    }

    /**
     * Setter ends
     * @param ends  Date 
     */
    public void setEnds(Date ends) {
        this.ends = ends;
    }
}
