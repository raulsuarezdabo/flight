
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.AirportEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
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
public class FlightDAOImpl implements FlightDAO {
    
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
     * Get all flights
     * @return List
     */
    @Override
    public List<FlightEntity> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from FlightEntity").list();
    }

    /**
     * Find flight by id
     * @param id    int
     * @return  AirportEntity   airport
     */
    @Override
    public FlightEntity findById(int id) {
        try {
            return (FlightEntity) this.sessionFactory.getCurrentSession().get(FlightEntity.class, id);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Find airport by code
     * @param code  String
     * @return  AirportEntity   airport
     */
    @Override
    public FlightEntity findByCode(String code) {
        try {
            Query query = this.sessionFactory.getCurrentSession().createQuery("FROM FlightEntity  WhERE code = :code ");
            query.setParameter("code", code);
            query.setMaxResults(1);
            return (FlightEntity) query.uniqueResult();
        } catch(HibernateException e) {
            return null;
        }
    }

    /**
     * Add new flight
     * @param flight    FlightEntity
     */
    @Override
    public void addFlight(FlightEntity flight) {
        this.sessionFactory.getCurrentSession().save(flight);
    }

    /**
     * Update an airport
     * @param flight    FlightEntity
     * @return AirportEntity    returns the entity or null if error
     */
    @Override
    public boolean updateFlight(FlightEntity flight) {
        try {
            this.sessionFactory.getCurrentSession().update(flight);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Delete flight
     * @param flight    FlightEntity
     * @return boolean  with success or not
     */
    @Override
    public boolean deleteFlight(FlightEntity flight) {
        try {
            this.sessionFactory.getCurrentSession().delete(flight);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
