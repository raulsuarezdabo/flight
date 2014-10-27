package com.mycompany.flight.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author raulsuarez
 */
@Entity
@Table(name = "City")
public class CityEntity implements Serializable {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    private String id;

    @Column(name = "Name", nullable = false)
    private String name;
    
    @Column(name = "CountryCode", nullable = false)
    private String countryCode;

    /**
     * Default constructor
     */
    public CityEntity() {
    }

    /**
     * Getter id
     * @return 
     */
    public String getId() {
        return id;
    }

    /**
     * Setter id
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter name
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Setter name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter countryCode
     * @return 
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Setter countryCode
     * @param countryCode 
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
