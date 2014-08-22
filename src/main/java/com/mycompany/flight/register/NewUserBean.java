package com.mycompany.flight.register;

/**
 *
 * @author raulsuarez
 */
public class NewUserBean {

    /**
     * Name of the user
     */
    private String name = "";
    /**
     * Surname of the user
     */
    private String surname = "";
    /**
     * email of the user
     */
    private String email = "";

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
}
