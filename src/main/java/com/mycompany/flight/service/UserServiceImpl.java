package com.mycompany.flight.service;

import com.mycompany.flight.dao.UserDAOImpl;
import com.mycompany.flight.entity.UserEntity;
import java.util.HashMap;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author raulsuarez
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOImpl userDAO;

    /**
     * property that contains the user to manage
     */
    private UserEntity user;

    /**
     * default constructor
     */
    public UserServiceImpl() {
        this.user = null;
    }

    /**
     * constructor with parameters
     *
     * @param user
     */
    public UserServiceImpl(UserEntity user) {
        this.user = user;
    }

    public boolean persist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public UserEntity newUser(boolean persist) {
        try {
//            user = new UserEntity();
//            user.setName("ejemplo");
//            user.setEmail("email@ejemplo");
//            user.setNif("12345678D");
//            user.setPhone("659327919");
//            this.userDAO.addUser(user);
//            return user;
            return new UserEntity();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public UserEntity updateUser(UserEntity user, HashMap values, boolean persist) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteUser(UserEntity user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserEntity getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserEntity getByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
