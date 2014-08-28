package com.mycompany.flight.entity;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * UserEntity that mapped from the DB model
 *
 * @author raulsuarez
 */
@Entity
@Table(name = "User")
public class UserEntity {

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "UserRole",
            joinColumns = {
                @JoinColumn(name = "UserID", referencedColumnName = "ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "RoleID", referencedColumnName = "ID")})
    private RoleEntity role;

    public UserEntity() {
    }

    public UserEntity(Integer id, String name, String surname, String email, String phone, Date birthDay, String address, String nif, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.birthDay = birthDay;
        this.address = address;
        this.nif = nif;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

}
