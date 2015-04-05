/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

/**
 *
 * @author raulsuarez
 */
@Entity
@Indexed
@Table(name = "Airport")
public class AirportEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Column(name = "Name", nullable = false)
    private String name;
    
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Column(name = "Code", unique = true, nullable = false)
    private String code;
    
    @IndexedEmbedded
    @OneToOne
    @JoinColumn(name = "CountryCode")
    private CountryEntity country;

    @IndexedEmbedded
    @OneToOne
    @JoinColumn(name = "CityId")
    private CityEntity city;

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

    /**
     * Getter city
     * @return city CityEntity
     */
    public CityEntity getCity() {
        return city;
    }

    /**
     * Setter city
     * @param city  CityEntty 
     */
    public void setCity(CityEntity city) {
        this.city = city;
    }
    
}
