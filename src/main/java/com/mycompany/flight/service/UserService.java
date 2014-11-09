package com.mycompany.flight.service;

import com.mycompany.flight.entity.CityEntity;
import com.mycompany.flight.entity.CountryEntity;
import com.mycompany.flight.entity.UserEntity;
import java.util.Date;
import java.util.HashMap;
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
     * @param password
     * @param name
     * @param surname
     * @param address
     * @param nif
     * @param phone
     * @param birthday
     * @return new User entity persisted or not depending on the persist @param
     */
    public UserEntity newUser(String email, String name, String surname, String address, String nif, String phone, Date birthday, CountryEntity country, CityEntity city, Locale locale);

    /**
     * Update the user entity
     *
     * @param user
     * @param values
     * @param persist
     * @return User entity updated and persisted depending the persist @param
     */
    public UserEntity updateUser(UserEntity user, HashMap values, boolean persist);

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
}
