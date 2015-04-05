/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.dao;

import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author raulsuarez
 */
@Repository
public class CountryDAOImpl implements CountryDAO {

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
     * Method to find one by code of the table Country
     *
     * @param code
     * @return CountryEntity
     */
    @Override
    public CountryEntity findById(String code) {
        try {
            CountryEntity country = (CountryEntity) this.entityManager.find(CountryEntity.class, code);
            return country;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Get all countries
     * @return List
     */
    @Override
    public List<CountryEntity> findAll() {
        List <CountryEntity> countries;
        countries = null;
        try {
            countries = this.entityManager.createQuery("FROM CountryEntity").getResultList();
        } catch (HibernateException e) {
            return null;
        } 
        return countries;
    }

}
