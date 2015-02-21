/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.dao;

import com.mycompany.flight.entity.UserEntity;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author raulsuarez
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Getter of the SessionFactory
     * @return SessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Setter sessionFactory
     * @param sessionFactory 
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Method to add new user on DB
     *
     * @param user
     */
    @Override
    public void addUser(UserEntity user) {
        this.sessionFactory.getCurrentSession().save(user);
    }

    /**
     * Method for updating the user entity
     * @param user
     * @return boolean  success or not flag
     */
    @Override
    public boolean updateUser(UserEntity user) {
        try {
            this.sessionFactory.getCurrentSession().update(user);
            return true;
        } catch ( Exception e ) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Method to obtain all user from DB
     *
     * @return
     */
    @Override
    public List<UserEntity> getAllUsers() {
        return this.sessionFactory.getCurrentSession().createQuery("from UserEntity").list();
    }

    /**
     * Method to delete the user entity
     *
     * @param user
     */
    @Override
    public void deleteUser(UserEntity user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Method to find the user by ID
     *
     * @param id
     * @return
     */
    @Override
    public UserEntity findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Method to find the user by email
     *
     * @param email
     * @return
     */
    @Override
    public UserEntity findByEmail(String email) {
        UserEntity user;
        Query query = this.sessionFactory.getCurrentSession().createQuery("FROM UserEntity  WhERE email = :email ");
        query.setParameter("email", email);
        query.setMaxResults(1);
        user = (UserEntity) query.uniqueResult();
        return user;
    }

}
