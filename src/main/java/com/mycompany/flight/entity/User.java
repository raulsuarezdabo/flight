package com.mycompany.flight.entity;

import java.util.Date;

/**
 *
 * @author raulsuarez
 */
public class User {

    /**
     *
     */
    private String name;
    /**
     *
     */
    private String surname;
    /**
     *
     */
    private Date birthday;
    /**
     *
     */
    private String email;
    /**
     *
     */
    private String phone;
    /**
     *
     */
    private String nif;
    /**
     *
     */
    private String address;
    /**
     *
     */
    private String password;

    /**
     * Getter of the name
     *
     * @return name of the User
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of the name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of the surname
     *
     * @return surname of the user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter of the surname
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter of the birthday
     *
     * @return birthday of the user
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Setter of the birthday
     *
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Getter of the email
     *
     * @return email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sette of the email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter of the phone
     *
     * @return current phone of the user
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter of the phone
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter of the nif of the user
     *
     * @return
     */
    public String getNif() {
        return nif;
    }

    /**
     * Setter of the nif
     *
     * @param nif current nif of the user
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Getter of the address
     *
     * @return address of the user
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter of the address
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter of the password
     *
     * @return password of the user (encrypted md5)
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of the password (encrypt in md5 by default)
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
