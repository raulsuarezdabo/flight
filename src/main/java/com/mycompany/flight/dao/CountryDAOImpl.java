/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.dao;

import com.mycompany.flight.entity.CountryEntity;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
            Session session = this.sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            CountryEntity role = (CountryEntity) session.get(CountryEntity.class, code);
            transaction.commit();
            return role;
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
            Session session = this.sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            countries = session.createQuery("FROM CountryEntity").list(); 
            transaction.commit();
        } catch (HibernateException e) {
            return null;
        } 
        return countries;
    }

}
