/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.AirportEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

/**
 * DAO that implements all abstract methods from the interface.
 * @author raulsuarez
 */
@Repository
public class AirportDAOImpl implements AirportDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Getter of the SessionFactory
     *
     * @return SessionFactory
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Setter sessionFactory
     *
     * @param entityManager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Get all airports
     * @return List
     */
    @Override
    public List<AirportEntity> findAll() {
        return this.entityManager.createQuery("from AirportEntity").getResultList();
    }

    /**
     * Find airport by code
     * @param code  String
     * @return  AirportEntity   airport
     */
    @Override
    public AirportEntity findByCode(String code) {
        try {
            Query query = this.entityManager.createQuery("FROM AirportEntity  WhERE code = :code ");
            query.setParameter("code", code);
            return (AirportEntity) query.getSingleResult();
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
            return (AirportEntity) this.entityManager.find(AirportEntity.class, id);
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
        this.entityManager.persist(airport);
    }

    /**
     * Update an airport
     * @param airport   AirportEntity
     * @return AirportEntity    returns the entity or null if error
     */
    @Override
    public boolean updateAirport(AirportEntity airport) {
        try {
            this.entityManager.merge(airport);
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
            this.entityManager.remove(airport);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
