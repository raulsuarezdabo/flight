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
    private Integer id;

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
    public Integer getId() {
        return id;
    }

    /**
     * Setter id
     * @param id 
     */
    public void setId(Integer id) {
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
    
    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof CityEntity == false) {
            return false;
        }
        CityEntity cityObject = (CityEntity) obj;
        if (this.id.equals(cityObject.getId())) {
            return true;
        }
        return false;
    }
}
