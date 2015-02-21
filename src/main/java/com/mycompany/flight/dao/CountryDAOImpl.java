/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.dao;

import com.mycompany.flight.entity.CountryEntity;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author raulsuarez
 */
@Repository
public class CountryDAOImpl implements CountryDAO {

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
     * Method to find one by code of the table Country
     *
     * @param code
     * @return CountryEntity
     */
    @Override
    public CountryEntity findById(String code) {
        try {
            CountryEntity country = (CountryEntity) this.sessionFactory.getCurrentSession().get(CountryEntity.class, code);
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
            countries = this.sessionFactory.getCurrentSession().createQuery("FROM CountryEntity").list(); 
        } catch (HibernateException e) {
            return null;
        } 
        return countries;
    }

}
