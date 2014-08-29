package com.mycompany.flight.service;

import com.mycompany.flight.entity.UserEntity;
import java.util.HashMap;

/**
 *
 * @author raulsuarez
 */
public interface UserService {

    /**
     * New entity user created
     *
     * @param persist
     * @return new User entity persisted or not depending on the persist @param
     */
    public UserEntity newUser(boolean persist);

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
