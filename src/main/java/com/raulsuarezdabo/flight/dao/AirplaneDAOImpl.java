
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.AirplaneEntity;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Airplane DAO implements all interface methods
 * @author raulsuarez
 */
@Repository
public class AirplaneDAOImpl implements AirplaneDAO {

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
     * Method to find all Airplanes
     * @return  List of airplanes
     */
    @Override
    public List<AirplaneEntity> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from AirplaneEntity").list();
    }

    /**
     * Method to find airplane by Id
     * @param id    Int
     * @return  AirplaneEntity
     */
    @Override
    public AirplaneEntity findById(int id) {
        try {
            return (AirplaneEntity) this.sessionFactory.getCurrentSession().get(AirplaneEntity.class, id);
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
        this.sessionFactory.getCurrentSession().save(airplane);
    }

    /**
     * Update airplane to DB
     * @param airplane  AirplaneEntity
     * @return  boolean returns true/false as sucessfull or not
     */
    @Override
    public boolean updateAirplane(AirplaneEntity airplane) {
        try {
            this.sessionFactory.getCurrentSession().update(airplane);
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
            this.sessionFactory.getCurrentSession().delete(airplane);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
