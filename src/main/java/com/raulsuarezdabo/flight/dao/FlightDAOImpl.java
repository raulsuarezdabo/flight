package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.ClassEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.SeatEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

/**
 * DAO that implements all abstract methods from the interface.
 *
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
     *
     * @return List
     */
    @Override
    public List<FlightEntity> findAll() {
        return this.entityManager.createQuery("from FlightEntity").getResultList();
    }

    /**
     * Find flight by id
     *
     * @param id int
     * @return AirportEntity airport
     */
    @Override
    public FlightEntity findById(int id) {
        try {
            return (FlightEntity) this.entityManager.find(FlightEntity.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Find airport by code
     *
     * @param code String
     * @return AirportEntity airport
     */
    @Override
    public FlightEntity findByCode(String code) {
        try {
            Query query = this.entityManager.createQuery("FROM FlightEntity  WhERE code = :code ");
            query.setParameter("code", code);
            return (FlightEntity) query.getSingleResult();
        } catch (HibernateException e) {
            return null;
        }
    }
    
    /**
     * Find flights by date of takes off
     * @param from  Date
     * @param to    Date
     * @return  List of Flights
     */
    @Override
    public List<FlightEntity> findByDate(Date from, Date to) {
        try {
            Query query = this.entityManager.createQuery("SELECT f "
                    + "FROM FlightEntity f "
                    + "WHERE f.start > :from AND f.start < :to "
                    + "ORDER BY f.start DESC"
            );

            query.setParameter("from", from);
            query.setParameter("to", to);
            return query.getResultList();
        } catch (HibernateException e) {
            return new ArrayList();
        }
    }
    
    /**
     * Find flights by date
     * @param date
     * @return 
     */
    @Override
    public List<FlightEntity> findByDate(Date date) {
        try {
            Query query = this.entityManager.createQuery("SELECT f "
                    + "FROM FlightEntity f "
                    + "WHERE day(f.start) = :day AND month(f.start) = :month AND year(f.start) = :year "
                    + "ORDER BY f.start DESC"
            );

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            query.setParameter("day", cal.get(Calendar.DAY_OF_MONTH));
            query.setParameter("month", cal.get(Calendar.MONTH) + 1);
            query.setParameter("year", cal.get(Calendar.YEAR));
            return query.getResultList();
        } catch(HibernateException e) {
            return new ArrayList();
        }
    }

    /**
     * Add new flight
     *
     * @param flight FlightEntity
     */
    @Override
    public void addFlight(FlightEntity flight) {
        this.entityManager.persist(flight);
    }

    /**
     * Update an airport
     *
     * @param flight FlightEntity
     * @return AirportEntity returns the entity or null if error
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
     *
     * @param flight FlightEntity
     * @return boolean with success or not
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

    /**
     * Find flights using cities criteria and Date.
     *
     * @param from CityEntity
     * @param to CityEntity
     * @param when Date
     * @return List Of FlightEntity
     */
    @Override
    public List<FlightEntity> findFlights(CityEntity from, CityEntity to, Date when) {
        try {
            Query query = this.entityManager.createQuery("SELECT f "
                    + "FROM FlightEntity f "
                    + "INNER JOIN f.airportFrom afrom "
                    + "INNER JOIN f.airportTo ato "
                    + "WHERE day(f.start) = :day AND month(f.start) = :month AND year(f.start) = :year "
                    + "AND afrom.city = :from AND ato.city = :to "
                    + "ORDER BY f.start DESC"
            );

            Calendar cal = Calendar.getInstance();
            cal.setTime(when);
            query.setParameter("day", cal.get(Calendar.DAY_OF_MONTH));
            query.setParameter("month", cal.get(Calendar.MONTH) + 1);
            query.setParameter("year", cal.get(Calendar.YEAR));
            query.setParameter("from", from);
            query.setParameter("to", to);
            return query.getResultList();
        } catch (HibernateException e) {
            return new ArrayList();
        }
    }
    
    /**
     * Method to find list of flights for tracking
     * @param days  int
     * @return  List
     */
    @Override
    public List findCountFlightsByDate(int days) {
        try {
            Query query = this.entityManager.createQuery("SELECT count(f.id), f.start "
                    + "FROM FlightEntity f "
                    + "WHERE f.start > current_date() "
                    + "GROUP BY day(f.start), month(f.start), year(f.start)"
                    + "ORDER BY f.start DESC"
            );
            List result = query.getResultList(); 
            if (result.size() >= days) {
                return result.subList(0, days);
            }
            else {
                return result.subList(0, result.size());
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return new ArrayList();
        }
    }

    /**
     * Set seats to an specific Flight
     *
     * @param flight FlightEntity
     * @param seats SeatEntity
     * @return boolean
     */
    @Override
    public boolean setSeatsToFlight(FlightEntity flight, Set<SeatEntity> seats, BookEntity book) {
        try {
            for (SeatEntity seat: seats) {
                if (this.setSeatToFlight(flight, seat, book) == false) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Set seat to an specific flight
     *
     * @param flight FlightEntity
     * @param seat SeatEntity
     * @param book
     * @return boolean
     */
    @Override
    public boolean setSeatToFlight(FlightEntity flight, SeatEntity seat, BookEntity book) {
        try {
            seat.setFlight(flight);
            //this.entityManager.persist(seat);
            seat.setBook(book);
            if (flight.isOffer() == false) {
                seat.setType(ClassEntity.TOURIST);
            }
            flight.addSeat(seat);
            this.entityManager.merge(flight);
            return true;
        } catch(Exception e) {
            System.out.println("fsdafdsadsfadsfas" + e.getMessage());
            return false;
        }
    }
    
    /**
     * 
     * @param flight    FlightEntity
     * @param type  int
     * @return  List of seats
     */
    @Override
    public List<SeatEntity> getSeatsByFlightClass(FlightEntity flight, int type) {
        try {
            Query query = this.entityManager.createQuery("SELECT f "
                    + "FROM SeatEntity s "
                    + "INNER JOIN s.flight f "
                    + "WHERE s.flight = :flight AND s.type = :type "
            );
            query.setParameter("flight", flight);
            query.setParameter("type", type);
            return query.getResultList();
                    
        } catch(Exception e) {
            return new ArrayList();
        }
    }

}
