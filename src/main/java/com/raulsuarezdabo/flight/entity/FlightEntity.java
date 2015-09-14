package com.raulsuarezdabo.flight.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * Class for managing flights
 * @author raulsuarez
 */
@Entity
@Indexed
@Table(name = "Flight")
public class FlightEntity implements Serializable {
    
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
    
    @IndexedEmbedded
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    /**
     * Getter Seat set
     * @return Seat of seats
     */
    public Set<SeatEntity> getSeats() {
        return seats;
    }

    /**
     * Setter seats set
     * @param seats 
     */
    public void setSeats(Set<SeatEntity> seats) {
        for(SeatEntity item: seats) {
            this.addSeat(item);
        }
    }
    
    /**
     * Add a seat to an specific flight
     * @param seat 
     */
    public void addSeat(SeatEntity seat) {
        Set <SeatEntity> currentSeats = this.seats;
        if (currentSeats.contains(seat) == false) {
            this.seats.add(seat);
        }
    }
    
    /**
     * Remove a seat from a list of seats
     * @param seat  SeatEntity
     */
    public void removeSeat(SeatEntity seat) {
        Set <SeatEntity> currentSeats = this.seats;
        if (currentSeats.contains(seat) == true) {
            this.seats.remove(seat);
        }
    }
    
    /**
     * Method to know if a flight it's inside of offer or not
     * @return  boolean
     */
    public boolean isOffer() {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        Date tomorrow = cal.getTime();
        if (this.start.after(now) && this.start.before(tomorrow)) {
            return true;
        }
        return false;
    }
    
}
