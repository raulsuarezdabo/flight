package com.mycompany.flight.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author raulsuarez
 */
@Entity
@Table(name = "Country")
public class CountryEntity {

    /**
     * Predefined roles to the users
     */
    public static final int USER_ROLE = 1;

    @Id
    @Column(name = "Code", unique = true, nullable = false)
    private int code;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Continent", nullable = false)
    private String continent;

    @Column(name = "Region", nullable = false)
    private String region;

    @Column(name = "SurfaceArea", nullable = false)
    private float surfaceArea;

    @Column(name = "IndepYear", nullable = false)
    private int indepYear;

    @Column(name = "Population", nullable = false)
    private double population;

    @Column(name = "LifeExpentacy", nullable = false)
    private float lifeExpentacy;

    @Column(name = "GNP", nullable = false)
    private float gnp;

    @Column(name = "GNPOld", nullable = false)
    private float gnpOld;

    @Column(name = "LocalName", nullable = false)
    private String localName;

    @Column(name = "GovernmentForm", nullable = false)
    private String governmentForm;

    @Column(name = "HeadOfState", nullable = false)
    private String headOfState;

    @Column(name = "Capital", nullable = false)
    private int capital;

    @Column(name = "Code2", nullable = false)
    private String code2;

    /**
     * Default constructor
     */
    public CountryEntity() {
    }

    /**
     * Parametrice constructor
     *
     * @param code
     * @param name
     * @param continent
     * @param region
     * @param surfaceArea
     * @param indepYear
     * @param population
     * @param lifeExpentacy
     * @param gnp
     * @param gnpOld
     * @param localName
     * @param governmentForm
     * @param headOfState
     * @param capital
     * @param code2
     */
    public CountryEntity(Integer code, String name, String continent, String region, float surfaceArea, int indepYear, double population, float lifeExpentacy, float gnp, float gnpOld, String localName, String governmentForm, String headOfState, int capital, String code2) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.surfaceArea = surfaceArea;
        this.indepYear = indepYear;
        this.population = population;
        this.lifeExpentacy = lifeExpentacy;
        this.gnp = gnp;
        this.gnpOld = gnpOld;
        this.localName = localName;
        this.governmentForm = governmentForm;
        this.headOfState = headOfState;
        this.capital = capital;
        this.code2 = code2;
    }

    /**
     * Getter code
     * @return int
     */
    public int getCode() {
        return code;
    }

    /**
     * Setter code
     * @param code 
     */
    public void setCode(int code) {
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
    public float getSurfaceArea() {
        return surfaceArea;
    }

    /**
     * Setter surfaceArea
     * @param surfaceArea 
     */
    public void setSurfaceArea(float surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    /**
     * Getter intepYear
     * @return 
     */
    public int getIndepYear() {
        return indepYear;
    }

    /**
     * Setter indepYeear
     * @param indepYear 
     */
    public void setIndepYear(int indepYear) {
        this.indepYear = indepYear;
    }

    /**
     * Getter population
     * @return double
     */
    public double getPopulation() {
        return population;
    }

    /**
     * Setter population
     * @param population 
     */
    public void setPopulation(double population) {
        this.population = population;
    }

    /**
     * Getter lifeExpentacy
     * @return float
     */
    public float getLifeExpentacy() {
        return lifeExpentacy;
    }

    /**
     * Setter lifeExpentacy
     * @param lifeExpentacy 
     */
    public void setLifeExpentacy(float lifeExpentacy) {
        this.lifeExpentacy = lifeExpentacy;
    }

    /**
     * Getter Gnp
     * @return float
     */
    public float getGnp() {
        return gnp;
    }

    /**
     * Setter Gnp
     * @param gnp 
     */
    public void setGnp(float gnp) {
        this.gnp = gnp;
    }

    /**
     * Getter GnpOld
     * @return float
     */
    public float getGnpOld() {
        return gnpOld;
    }

    /**
     * Setter gnpOld
     * @param gnpOld 
     */
    public void setGnpOld(float gnpOld) {
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
    public int getCapital() {
        return capital;
    }

    /**
     * Setter capital
     * @param capital 
     */
    public void setCapital(int capital) {
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

}
