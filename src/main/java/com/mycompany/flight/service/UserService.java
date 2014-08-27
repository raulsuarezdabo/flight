package com.mycompany.flight.service;

import com.mycompany.flight.entity.User;
import java.util.HashMap;

/**
 *
 * @author raulsuarez
 */
public interface UserService {

    /**
     * New entity user created
     *
     * @param values
     * @param persist
     * @return new User entity persisted or not depending on the persist @param
     */
    public User newUser(HashMap values, boolean persist);

    /**
     * Update the user entity
     *
     * @param user
     * @param values
     * @param persist
     * @return User entity updated and persisted depending the persist @param
     */
    public User updateUser(User user, HashMap values, boolean persist);

    /**
     * Delete the current user entity
     *
     * @param user
     * @return
     */
    public boolean deleteUser(User user);

    /**
     * Getter by id
     *
     * @param id
     * @return
     */
    public User getById(int id);

    /**
     * Getter by email (unique on the DB)
     *
     * @param email
     * @return
     */
    public User getByEmail(String email);
}
