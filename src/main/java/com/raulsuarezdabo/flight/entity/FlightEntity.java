package com.raulsuarezdabo.flight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class for managing flights
 * @author raulsuarez
 */
@Entity
@Table(name = "Flight")
public class FlightEntity {
    
    public static final int STATUSNONE = 0;
    public static final int STATUSAVAILABLE = 1;
    
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    
    @Column(name = "Status", nullable = false)
    private int status;
    
    @OneToOne
    @JoinColumn(name = "AirportFrom")
    private AirportEntity airportFrom;

    @OneToOne
    @JoinColumn(name = "AirportTo")
    private AirportEntity airportTo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Start", nullable = false)
    private Date start;
    
    @Temporal(TemporalType.TIME)
    @Column(name = "Time", nullable = false)
    private Date time;
    
    @OneToOne
    @JoinColumn(name = "Airplane")
    private AirplaneEntity airplane;
    
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "Seat", 
//        joinColumns = {
//            @JoinColumn(name = "Flight", referencedColumnName = "ID")})
//    private List<SeatEntity> seats;
    
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
     * Getter time
     * @return  int
     */
    public Date getTime() {
        return time;
    }

    /**
     * Setter time
     * @param time  int 
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Getter airplaneEntity
     * @return  AirplaneEntity
     */
    public AirplaneEntity getAirplane() {
        return airplane;
    }

    /**
     * Setter airplane
     * @param airplane  AirplaneEntity 
     */
    public void setAirplane(AirplaneEntity airplane) {
        this.airplane = airplane;
    }

//    /**
//     * Getter seats
//     * @return  List of the seats
//     */
//    public List<SeatEntity> getSeats() {
//        return seats;
//    }
//
//    /**
//     * Setter list of seats
//     * @param seats     List
//     */
//    public void setSeats(List<SeatEntity> seats) {
//        this.seats = seats;
//    }
}
