package com.raulsuarezdabo.flight.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 *
 * @author raulsuarez
 */
@Entity
@Indexed
@Table(name = "City")
public class CityEntity implements Serializable {
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    private int id;

    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Column(name = "Name", nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "CountryCode", nullable = false)
    private CountryEntity country;

    /**
     * Default constructor
     */
    public CityEntity() {
    }

    /**
     * Getter id
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Setter id
     * @param id 
     */
    public void setId(int id) {
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
    public CountryEntity getCountryCode() {
        return country;
    }

    /**
     * Setter countryCode
     * @param countryCode 
     */
    public void setCountryCode(CountryEntity countryCode) {
        this.country = countryCode;
    }
}
