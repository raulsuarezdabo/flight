/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import com.raulsuarezdabo.flight.entity.RoleEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public boolean deleteUser(int user);

    /**
     * Getter by id
     *
     * @param id
     * @return
     */
    public UserEntity getById(int id);
    
    /**
     * Get all users
     * @return  List of users
     */
    public List<UserEntity> getAll();

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
    
    /**
     * Method for adding new user with a specific roles
     * @param email String   
     * @param name  String
     * @param surname   String
     * @param address   String
     * @param nif   String
     * @param phone String
     * @param birthday  Date
     * @param country   CountryEntity
     * @param city  CityEntity
     * @param roles List of roles
     * @return  UserEntity
     */
    public UserEntity addUser(String email, String name, String surname, String address, String nif, String phone, Date birthday, CountryEntity country, CityEntity city, List<RoleEntity> roles);
    
    /**
     * Method for returning a report for chart
     * @return  List
     */
    public List getChart();
    
}
