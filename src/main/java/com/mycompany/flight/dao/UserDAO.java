package com.mycompany.flight.dao;

import com.raulsuarezdabo.flight.entity.UserEntity;
import java.util.List;

/**
 *
 * @author raulsuarez
 */
public interface UserDAO {

    /**
     * New user created on DB
     *
     * @param user
     */
    public void addUser(UserEntity user);

    /**
     * Method for updating user Entity on DB
     * @param user
     * @return boolean  success or error updating the user on DB
     */
    public boolean updateUser(UserEntity user);

    /**
     * List of all user on the DB
     *
     * @return List on the table
     */
    public List<UserEntity> getAllUsers();

    /**
     * Delete the specific user
     *
     * @param user
     */
    public void deleteUser(UserEntity user);

    /**
     * Find by id on the user table
     *
     * @param id
     * @return UserEntity with the param asociated or null
     */
    public UserEntity findById(Integer id);

    /**
     * Find by email on the user table
     *
     * @param email
     * @return UserEntity with the param asociated or null
     */
    public UserEntity findByEmail(String email);

}
