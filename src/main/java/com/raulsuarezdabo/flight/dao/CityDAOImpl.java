/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.dao;

import com.raulsuarezdabo.flight.dao.CityDAO;
import com.raulsuarezdabo.flight.entity.CityEntity;
import com.raulsuarezdabo.flight.entity.CountryEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.HibernateException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CityDAOImpl implements CityDAO {

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
     * Method to find one by code of the table Country
     *
     * @return CountryEntity
     */
    @Override
    public CityEntity findById(int id) {
        try {
            CityEntity city = this.entityManager.find(CityEntity.class, id);
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
            cities = this.entityManager.createQuery("FROM CityEntity").getResultList();
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
            Query query = this.entityManager.createQuery("FROM CityEntity  WhERE country = :country ");
            query.setParameter("country", country);
            cities = query.getResultList();
            return cities;
        } catch(HibernateException e) {
            return null;
        }
    }

}
