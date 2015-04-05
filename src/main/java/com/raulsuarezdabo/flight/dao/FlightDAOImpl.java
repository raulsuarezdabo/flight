
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.FlightEntity;
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
public class FlightDAOImpl implements FlightDAO {
    
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
     * Get all flights
     * @return List
     */
    @Override
    public List<FlightEntity> findAll() {
        return this.entityManager.createQuery("from FlightEntity").getResultList();
    }

    /**
     * Find flight by id
     * @param id    int
     * @return  AirportEntity   airport
     */
    @Override
    public FlightEntity findById(int id) {
        try {
            return (FlightEntity) this.entityManager.find(FlightEntity.class, id);
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
            Query query = this.entityManager.createQuery("FROM FlightEntity  WhERE code = :code ");
            query.setParameter("code", code);
            return (FlightEntity) query.getSingleResult();
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
        this.entityManager.persist(flight);
    }

    /**
     * Update an airport
     * @param flight    FlightEntity
     * @return AirportEntity    returns the entity or null if error
     */
    @Override
    public boolean updateFlight(FlightEntity flight) {
        try {
            this.entityManager.merge(flight);
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
            this.entityManager.remove(flight);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
