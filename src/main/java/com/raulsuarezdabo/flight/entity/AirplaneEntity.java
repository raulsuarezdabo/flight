
package com.raulsuarezdabo.flight.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author raulsuarez
 */
@Entity
@Table(name = "Airplane")
public class AirplaneEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    
    @Column(name = "Model", nullable = false)
    private String model;
    
    @Column(name = "Maker", nullable = false)
    private String maker;
    
    @Column(name = "Year", nullable = false)
    private Integer year;
    
    @Column(name = "Num_seats_total", nullable = false)
    private Integer numSeatsTotal;
    
    @Column(name = "Num_seats_tourist", nullable = false)
    private Integer numSeatsTourist;
    
    @Column(name = "Num_seats_business", nullable = false)
    private Integer numSeatsBusiness;
    
    @Column(name = "Num_seats_offer", nullable = false)
    private Integer numSeatsOffer;
    
    

    /**
     * Getter of id
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Setter id
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter of the model name
     * @return String
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter of the model name
     * @param model     String
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter maker
     * @return  String
     */
    public String getMaker() {
        return maker;
    }

    /**
     * Setter maker
     * @param maker String 
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    /**
     * Getter year
     * @return Integer
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Setter year
     * @param year  Integer 
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Getter number total seats
     * @return Integer
     */
    public Integer getNumSeatsTotal() {
        return numSeatsTotal;
    }

    /**
     * Setter of number total seats
     * @param numSeatsTotal     Integer
     */
    public void setNumSeatsTotal(Integer numSeatsTotal) {
        this.numSeatsTotal = numSeatsTotal;
    }

    /**
     * Getter number of tourist seats
     * @return Integer
     */
    public Integer getNumSeatsTourist() {
        return numSeatsTourist;
    }

    /**
     * Setter number of tourist seats
     * @param numSeatsTourist   Integer
     */
    public void setNumSeatsTourist(Integer numSeatsTourist) {
        this.numSeatsTourist = numSeatsTourist;
    }

    /**
     * Getter Number business seats
     * @return  Integer
     */
    public Integer getNumSeatsBusiness() {
        return numSeatsBusiness;
    }

    /**
     * Setter number Business seats
     * @param numSeatsBusiness  Integer
     */
    public void setNumSeatsBusiness(Integer numSeatsBusiness) {
        this.numSeatsBusiness = numSeatsBusiness;
    }

    /**
     * Getter number offer seats
     * @return  Integer
     */
    public Integer getNumSeatsOffer() {
        return numSeatsOffer;
    }

    /**
     * Setter number offer seats
     * @param numSeatsOffer     Integer
     */
    public void setNumSeatsOffer(Integer numSeatsOffer) {
        this.numSeatsOffer = numSeatsOffer;
    }
    
}
