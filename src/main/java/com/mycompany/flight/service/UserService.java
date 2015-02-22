package com.mycompany.flight.service;

import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import com.mycompany.flight.entity.UserEntity;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author raulsuarez
 */
public interface UserService {

    /**
     * New entity user created
     *
     * @param email
     * @param name
     * @param surname
     * @param address
     * @param nif
     * @param phone
     * @param birthday
     * @param country
     * @param city
     * @param locale
     * @return new User entity persisted or not depending on the persist @param
     */
    public UserEntity newUser(String email, String name, String surname, String address, String nif, String phone, Date birthday, CountryEntity country, CityEntity city, Locale locale);

    /**
     * Update the user entity
     *
     * @param email
     * @param editUser
     * @param persist
     * @return User entity updated and persisted depending the persist @param
     */
    public UserEntity updateUser(String email, UserEntity editUser, boolean persist);

    /**
     * Delete the current user entity
     *
     * @param user
     * @return
     */
    public boolean deleteUser(UserEntity user);

    /**
     * Getter by id
     *
     * @param id
     * @return
     */
    public UserEntity getById(int id);

    /**
     * Getter by email (unique on the DB)
     *
     * @param email
     * @return
     */
    public UserEntity getByEmail(String email);

    /**
     * Process to recover the access by e-mail
     * @param email
     * @param current
     * @return success or not
     */
    public Boolean forgotAccount(String email, Locale current);

    /**
     * method that check if the credentials are correct
     * @param email
     * @param password
     * @return 
     */
    public boolean checkCredentails(String email, String password);
    
    /**
     * Method that check over spring security if is logged the user or not
     * @return boolean
     */
    public boolean isLogged();
    
    /**
     * Method that get the currently user logged
     * @return UserEntity
     */
    public UserEntity getLoggedUser();
    
    /**
     * Method that destroy the current user's session
     * @return boolean
     */
    public boolean logout();
}
