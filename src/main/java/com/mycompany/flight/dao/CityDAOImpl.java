/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.dao;

import com.mycompany.flight.entity.CityEntity;
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
public class CityDAOImpl implements CityDAO {

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
     * @return CountryEntity
     */
    @Override
    public CityEntity findById(int id) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            CityEntity city = (CityEntity) session.get(CityEntity.class, id);
            transaction.commit();
            return city;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Get all countries
     * @return List
     */
    @Override
    public List<CityEntity> findAll() {
        List <CityEntity> cities;
        cities = null;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            cities = session.createQuery("FROM CityEntity").list(); 
            transaction.commit();
        } catch (HibernateException e) {
            return null;
        } 
        return cities;
    }

    @Override
    public List<CityEntity> findByCountry(CountryEntity country) {
        List <CityEntity> cities;
        cities = null;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM CityEntity  WhERE countryCode = :country ");
            query.setParameter("country", country.getCode());
            cities = query.list();
            transaction.commit();
            return cities;
        } catch(HibernateException e) {
            return null;
        }
    }

}
