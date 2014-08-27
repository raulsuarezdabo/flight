package com.mycompany.flight.register;

import java.util.Date;

/**
 *
 * @author raulsuarez
 */
public class NewUserBean {

    /**
     * Name of the user
     */
    private String name;
    /**
     * Surname of the user
     */
    private String surname;
    /**
     * email of the user
     */
    private String email;
    /**
     * birthday of the user
     */
    private Date birthday;

    /**
     * phone of the user
     */
    private String phone;

    /**
     * nif of the user
     */
    private String nif;

    /**
     * address of the user
     */
    private String address;

    /**
     * password of the user
     */
    private String password;

    /**
     * Getter of name
     *
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     *
     * @param name name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of surname
     *
     * @return String
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter of surname
     *
     * @param surname surname of the user
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter of the user
     *
     * @return email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of the email
     *
     * @param email email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter of the birthday
     * @return 
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Setter of the birthday
     * @param birthday 
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Getter of the phone
     * @return 
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter of the phone
     * @param phone 
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter of the nif
     * @return 
     */
    public String getNif() {
        return nif;
    }

    /**
     * Setter of the nif
     * @param nif 
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Getter of the address
     * @return 
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter of the address
     * @param address 
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter of the password
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of the password
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Method that creates the new user
     * @return 
     */
    public Boolean singUpAction() {
        return false;
    }
    
}
