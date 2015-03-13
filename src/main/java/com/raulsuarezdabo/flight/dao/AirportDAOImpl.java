/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.AirportEntity;
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
public class AirportDAOImpl implements AirportDAO {

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
     * Get all airports
     * @return List
     */
    @Override
    public List<AirportEntity> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from AirportEntity").list();
    }

    /**
     * Find airport by code
     * @param code  String
     * @return  AirportEntity   airport
     */
    @Override
    public AirportEntity findByCode(String code) {
        try {
            Query query = this.sessionFactory.getCurrentSession().createQuery("FROM AirportEntity  WhERE code = :code ");
            query.setParameter("code", code);
            query.setMaxResults(1);
            return (AirportEntity) query.uniqueResult();
        } catch(HibernateException e) {
            return null;
        }
    }
    
    /**
     * Find airport by code
     * @param id    int
     * @return  AirportEntity   airport
     */
    @Override
    public AirportEntity findById(int id) {
        try {
            return (AirportEntity) this.sessionFactory.getCurrentSession().get(AirportEntity.class, id);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Add new airport
     * @param airport   AirportEntity
     */
    @Override
    public void addAirport(AirportEntity airport) {
        this.sessionFactory.getCurrentSession().save(airport);
    }

    /**
     * Update an airport
     * @param airport   AirportEntity
     * @return AirportEntity    returns the entity or null if error
     */
    @Override
    public boolean updateAirport(AirportEntity airport) {
        try {
            this.sessionFactory.getCurrentSession().update(airport);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Delete an airport
     * @param airport   AirportEntity
     * @return boolean  with success or not
     */
    @Override
    public boolean deleteAirport(AirportEntity airport) {
        try {
            this.sessionFactory.getCurrentSession().delete(airport);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
