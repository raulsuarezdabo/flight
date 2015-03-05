/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.AirlineEntity;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO that implements all abstract methods from the interface.
 * @author raulsuarez
 */
@Repository
public class AirlineDAOImpl implements AirlineDAO {

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
     * Get all airlines
     * @return List
     */
    @Override
    public List<AirlineEntity> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from AirlineEntity").list();
    }

    /**
     * Find airline by code
     * @param code  String
     * @return  AirlineEntity   airline
     */
    @Override
    public AirlineEntity findByCode(String code) {
        try {
            Query query = this.sessionFactory.getCurrentSession().createQuery("FROM AirlineEntity  WhERE code = :code ");
            query.setParameter("code", code);
            query.setMaxResults(1);
            return (AirlineEntity) query.uniqueResult();
        } catch(HibernateException e) {
            return null;
        }
    }
    
    /**
     * Find airline by code
     * @param id    int
     * @return  AirlineEntity   airline
     */
    @Override
    public AirlineEntity findById(int id) {
        try {
            return (AirlineEntity) this.sessionFactory.getCurrentSession().get(AirlineEntity.class, id);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Add new airline
     * @param airline   AirlineEntity
     */
    @Override
    public void addAirline(AirlineEntity airline) {
        this.sessionFactory.getCurrentSession().save(airline);
    }

    /**
     * Update an airline
     * @param airline   AirlineEntity
     * @return AirlineEntity    returns the entity or null if error
     */
    @Override
    public boolean updateAirline(AirlineEntity airline) {
        try {
            this.sessionFactory.getCurrentSession().update(airline);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Delete an airline
     * @param airline   AirlineEntity
     * @return boolean  with success or not
     */
    @Override
    public boolean deleteAirline(AirlineEntity airline) {
        try {
            this.sessionFactory.getCurrentSession().delete(airline);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
