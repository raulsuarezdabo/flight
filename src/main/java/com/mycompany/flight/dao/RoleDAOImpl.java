/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.dao;

import com.raulsuarezdabo.flight.entity.RoleEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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
     * @param name alias for this role's name
     * @return 
     */
    @Override
    public RoleEntity findByName(String name) {
        try {
            Query query = this.sessionFactory.getCurrentSession().createQuery("FROM RoleEntity  WhERE name = :name ");
            query.setParameter("name", name);
            query.setMaxResults(1);
            RoleEntity role = (RoleEntity) query.uniqueResult();
            return role;
        } catch(Exception ex) {
            return null;
        }
    }

}
