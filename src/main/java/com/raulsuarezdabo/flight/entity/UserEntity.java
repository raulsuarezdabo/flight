package com.raulsuarezdabo.flight.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * UserEntity that mapped from the DB model
 *
 * @author raulsuarez
 */
@Entity
@Table(name = "User")
public class UserEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "BirthDay")
    private Date birthDay;

    @Column(name = "Address")
    private String address;

    @Column(name = "Nif")
    private String nif;

    @Column(name = "Password")
    private String password;

    @OneToOne
    @JoinColumn(name = "CountryCode")
    private CountryEntity country;

    @OneToOne
    @JoinColumn(name = "CityId")
    private CityEntity city;

    @Column(name = "Token")
    private String token;


    /* Spring Security fields*/
    /**
     * Role List with the avaible Roles
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UserRole",
            joinColumns = {
                @JoinColumn(name = "UserID", referencedColumnName = "ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "RoleID", referencedColumnName = "ID")})
    private List<RoleEntity> authorities;

    /**
     * Boolean for controlling if the account is expired
     */
    @Transient
    private boolean accountNonExpired = true;

    /**
     * Boolean for controlling if the account is locked
     */
    @Transient
    private boolean accountNonLocked = true;

    /**
     * Boolean for controlling if the credentials has been expired
     */
    @Transient
    private boolean credentialsNonExpired = true;

    /**
     * Boolean for controlling if have been enabled or not
     */
    @Transient
    private boolean enabled = true;

    public UserEntity() {
    }

    public UserEntity(Integer id, String name, String surname, String email, String phone, Date birthDay, String address, String nif, String password, CountryEntity country, CityEntity city, Date createdAt) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.birthDay = birthDay;
        this.address = address;
        this.nif = nif;
        this.password = password;
        this.country = country;
        this.city = city;
    }

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
     * Getter name property
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter name property
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter surname property
     *
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter surname property
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter email property
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter email property
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter phone property
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter phone property
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter birthday property
     *
     * @return
     */
    public Date getBirthDay() {
        return birthDay;
    }

    /**
     * Setter birthday property
     *
     * @param birthDay
     */
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * Getter address property
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter address property
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter nif property
     *
     * @return
     */
    public String getNif() {
        return nif;
    }

    /**
     * Setter nif property
     *
     * @param nif
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Getter password property
     *
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Setter password property
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter role property
     *
     * @return
     */
    public List<RoleEntity> getRole() {
        return authorities;
    }

    /**
     * Setter role property
     *
     * @param role
     */
    public void setRole(List<RoleEntity> role) {
        this.authorities = role;
    }

    /**
     * Method for adding a role on the List of roles
     *
     * @param role
     */
    public void addRole(RoleEntity role) {
        if (this.authorities == null) {
            this.authorities = new ArrayList<>();
        }
        this.authorities.add(role);
    }

    /**
     * Getter of countryentity
     *
     * @return CountryEntity
     */
    public CountryEntity getCountry() {
        return country;
    }

    /**
     * Setter countryEntity
     *
     * @param country CountryEntity
     */
    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    /**
     * Getter for city entity
     *
     * @return
     */
    public CityEntity getCity() {
        return this.city;
    }

    /**
     * Setter city entity
     *
     * @param city
     */
    public void setCity(CityEntity city) {
        this.city = city;
    }

    /**
     * Getter token
     * @return  String  current token
     */
    public String getToken() {
        return token;
    }

    /**
     * Setter token 
     * @param token     String
     */
    public void setToken(String token) {
        this.token = token;
    }
    
    /**
     * Getter for name
     *
     * @return String
     */
    @Override
    public String getUsername() {
        return this.name;
    }

    /**
     * Getter for accountNonExpired
     *
     * @return booelan
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    /**
     * Setter for accountNonExpired
     *
     * @param accountNonExpired boolean
     */
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    /**
     * Getter for the accountLocket
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    /**
     * Setter for locking the account
     *
     * @param accountNonLocked boolean
     */
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    /**
     * Getter for credentialsNonExpired
     *
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    /**
     * Setter for credentialsNonExpired
     *
     * @param credentialsNonExpired boolean
     */
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    /**
     * Getter for enabled
     *
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Getter of Authorities
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        try {
            return this.authorities;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
