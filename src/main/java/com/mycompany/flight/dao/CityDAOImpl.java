/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.dao;

import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
            CityEntity city = (CityEntity) this.sessionFactory.getCurrentSession().get(CityEntity.class, id);
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
            cities = this.sessionFactory.getCurrentSession().createQuery("FROM CityEntity").list();
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
            Query query = this.sessionFactory.getCurrentSession().createQuery("FROM CityEntity  WhERE countryCode = :country ");
            query.setParameter("country", country.getCode());
            cities = query.list();
            return cities;
        } catch(HibernateException e) {
            return null;
        }
    }

}
