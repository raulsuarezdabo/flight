package com.raulsuarezdabo.flight.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "Country")
public class CountryEntity implements Serializable {
    
    @Id
    @Column(name = "Code", unique = true, nullable = false)
    private String code;

    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Continent", nullable = false)
    private String continent;

    @Column(name = "Region", nullable = false)
    private String region;

    @Column(name = "SurfaceArea", nullable = false)
    private Integer surfaceArea;

    @Column(name = "IndepYear", nullable = false)
    private Float indepYear;

    @Column(name = "Population", nullable = false)
    private Integer population;

    @Column(name = "GNP", nullable = false)
    private Float gnp;

    @Column(name = "GNPOld", nullable = false)
    private Float gnpOld;

    @Column(name = "LocalName", nullable = false)
    private String localName;

    @Column(name = "GovernmentForm", nullable = false)
    private String governmentForm;

    @Column(name = "HeadOfState", nullable = false)
    private String headOfState;

    @Column(name = "Capital", nullable = false)
    private Integer capital;

    @Column(name = "Code2", nullable = false)
    private String code2;
    
    @IndexedEmbedded
    @OneToMany(mappedBy = "country")
    private Set<CityEntity> cities = new HashSet<>();

    /**
     * Default constructor
     */
    public CountryEntity() {
    }
    
    /**
     * Getter code
     * @return int
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter code
     * @param code 
     */
    public void setCode(String code) {
        this.code = code;
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
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter continent
     * @return String
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Setter continent
     * @param continent 
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * Getter region
     * @return String
     */
    public String getRegion() {
        return region;
    }

    /**
     * Setter region
     * @param region 
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Getter surfaceArea
     * @return float
     */
    public Integer getSurfaceArea() {
        return surfaceArea;
    }

    /**
     * Setter surfaceArea
     * @param surfaceArea 
     */
    public void setSurfaceArea(Integer surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    /**
     * Getter intepYear
     * @return 
     */
    public Float getIndepYear() {
        return indepYear;
    }

    /**
     * Setter indepYeear
     * @param indepYear 
     */
    public void setIndepYear(Float indepYear) {
        this.indepYear = indepYear;
    }

    /**
     * Getter population
     * @return double
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     * Setter population
     * @param population 
     */
    public void setPopulation(Integer population) {
        this.population = population;
    }

    /**
     * Getter Gnp
     * @return float
     */
    public Float getGnp() {
        return gnp;
    }

    /**
     * Setter Gnp
     * @param gnp 
     */
    public void setGnp(Float gnp) {
        this.gnp = gnp;
    }

    /**
     * Getter GnpOld
     * @return float
     */
    public Float getGnpOld() {
        return gnpOld;
    }

    /**
     * Setter gnpOld
     * @param gnpOld 
     */
    public void setGnpOld(Float gnpOld) {
        this.gnpOld = gnpOld;
    }

    /**
     * Getter localName
     * @return String
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * Setter localName
     * @param localName 
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    /**
     * Getter government
     * @return String
     */
    public String getGovernmentForm() {
        return governmentForm;
    }

    /**
     * Setter governmentForm
     * @param governmentForm 
     */
    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    /**
     * Getter headOfState
     * @return 
     */
    public String getHeadOfState() {
        return headOfState;
    }

    /**
     * Setter of headOfState
     * @param headOfState 
     */
    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    /**
     * Getter capital
     * @return int
     */
    public Integer getCapital() {
        return capital;
    }

    /**
     * Setter capital
     * @param capital 
     */
    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    /**
     * Getter Code2
     * @return String
     */
    public String getCode2() {
        return code2;
    }

    /**
     * Setter code2
     * @param code2 
     */
    public void setCode2(String code2) {
        this.code2 = code2;
    }

    /**
     * method to override the object to string
     * @return 
     */
    @Override
    public String toString() {
        System.out.println("CountryEntity toString:" + this.name);
        return this.code;
    }

    /**
     * Getter cities
     * @return  List
     */
    public Set<CityEntity> getCities() {
        return cities;
    }

    /**
     * Setter cities
     * @param cities    List 
     */
    public void setCities(Set<CityEntity> cities) {
        this.cities = cities;
    }
}
