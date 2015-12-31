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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author raulsuarez
 */
@Entity
@Table(name = "Airline")
public class AirlineEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    
    @Column(name = "Name", nullable = false)
    private String name;
    
    @Column(name = "Code", unique = true, nullable = false)
    private String code;
    
    @OneToOne
    @JoinColumn(name = "CountryCode")
    private CountryEntity country;

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
     * Getter name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Setter name
     * @param name  String 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter code
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter code
     * @param code  String 
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter country
     * @return CountryEntity
     */
    public CountryEntity getCountry() {
        return country;
    }

    /**
     * Setter country
     * @param country   CountryEntity 
     */
    public void setCountry(CountryEntity country) {
        this.country = country;
    }
    
}
