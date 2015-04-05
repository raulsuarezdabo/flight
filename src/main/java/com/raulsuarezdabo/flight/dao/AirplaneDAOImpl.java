
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Airplane DAO implements all interface methods
 * @author raulsuarez
 */
@Repository
public class AirplaneDAOImpl implements AirplaneDAO {

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
     * Method to find all Airplanes
     * @return  List of airplanes
     */
    @Override
    public List<AirplaneEntity> findAll() {
        return this.entityManager.createQuery("from AirplaneEntity").getResultList();
    }

    /**
     * Method to find airplane by Id
     * @param id    Int
     * @return  AirplaneEntity
     */
    @Override
    public AirplaneEntity findById(int id) {
        try {
            return (AirplaneEntity) this.entityManager.find(AirplaneEntity.class, id);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Add airplane to the db
     * @param airplane AirplaneEntity
     */
    @Override
    public void addAirplane(AirplaneEntity airplane) {
        this.entityManager.persist(airplane);
    }

    /**
     * Update airplane to DB
     * @param airplane  AirplaneEntity
     * @return  boolean returns true/false as sucessfull or not
     */
    @Override
    public boolean updateAirplane(AirplaneEntity airplane) {
        try {
            this.entityManager.merge(airplane);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Method to delete an airplane from db
     * @param airplane  AirplaneEntity
     * @return  boolean return true/false as successfull or not
     */
    @Override
    public boolean deleteAirplane(AirplaneEntity airplane) {
        try {
            this.entityManager.remove(airplane);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
