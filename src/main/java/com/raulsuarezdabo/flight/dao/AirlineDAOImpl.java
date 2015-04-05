/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.AirlineEntity;
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
public class AirlineDAOImpl implements AirlineDAO {

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
     * Get all airlines
     * @return List
     */
    @Override
    public List<AirlineEntity> findAll() {
        return this.entityManager.createQuery("from AirlineEntity").getResultList();
    }

    /**
     * Find airline by code
     * @param code  String
     * @return  AirlineEntity   airline
     */
    @Override
    public AirlineEntity findByCode(String code) {
        try {
            Query query = this.entityManager.createQuery("FROM AirlineEntity  WhERE code = :code ");
            query.setParameter("code", code);
            return (AirlineEntity) query.getSingleResult();
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
            return (AirlineEntity) this.entityManager.find(AirlineEntity.class, id);
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
        this.entityManager.persist(airline);
    }

    /**
     * Update an airline
     * @param airline   AirlineEntity
     * @return AirlineEntity    returns the entity or null if error
     */
    @Override
    public boolean updateAirline(AirlineEntity airline) {
        try {
            this.entityManager.merge(airline);
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
            this.entityManager.remove(airline);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
