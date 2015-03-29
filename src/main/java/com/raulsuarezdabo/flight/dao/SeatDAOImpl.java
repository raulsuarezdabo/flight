package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.SeatEntity;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * seat DAO implements all interface methods
 * @author raulsuarez
 */
@Repository
public class SeatDAOImpl implements SeatDAO {
    
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
     * Method to find all seats
     * @return  List of airplanes
     */
    @Override
    public List<SeatEntity> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from SeatEntity").list();
    }

    /**
     * Method to find seat by Id
     * @param id    Int
     * @return  SeatEntity
     */
    @Override
    public SeatEntity findById(int id) {
        try {
            return (SeatEntity) this.sessionFactory.getCurrentSession().get(SeatEntity.class, id);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Add seat to the db
     * @param seat SeatEntity
     */
    @Override
    public void addSeat(SeatEntity seat) {
        this.sessionFactory.getCurrentSession().save(seat);
    }

    /**
     * Update seat to DB
     * @param seat  SeatEntity
     * @return  boolean returns true/false as sucessfull or not
     */
    @Override
    public boolean updateSeat(SeatEntity seat) {
        try {
            this.sessionFactory.getCurrentSession().update(seat);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Method to delete an seat from db
     * @param seat  SeatEntity
     * @return  boolean return true/false as successfull or not
     */
    @Override
    public boolean deleteSeat(SeatEntity seat) {
        try {
            this.sessionFactory.getCurrentSession().delete(seat);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
}
