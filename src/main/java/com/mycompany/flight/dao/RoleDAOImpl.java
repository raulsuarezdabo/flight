/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.dao;

import com.mycompany.flight.entity.RoleEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author raulsuarez
 */
@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Getter of the SessionFactory
     *
     * @return SessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Setter sessionFactory
     *
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Method to find RoleEntity by id
     * @param id
     * @return 
     */
    @Override
    public RoleEntity findById(Integer id) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            RoleEntity role = (RoleEntity) session.get(RoleEntity.class, id);
            transaction.commit();
            return role;
        } catch(Exception ex) {
            return null;
        }
    }

}
