/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight.dao;

import com.raulsuarezdabo.flight.entity.RoleEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author raulsuarez
 */
@Repository
public class RoleDAOImpl implements RoleDAO {

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
     * Method to find RoleEntity by id
     * @param name alias for this role's name
     * @return 
     */
    @Override
    public RoleEntity findByName(String name) {
        try {
            Query query = this.entityManager.createQuery("FROM RoleEntity  WhERE name = :name ");
            query.setParameter("name", name);
            RoleEntity role = (RoleEntity) query.getSingleResult();
            return role;
        } catch(Exception ex) {
            return null;
        }
    }
    
    @Override
    public List<RoleEntity> findAll() {
        return this.entityManager.createQuery("FROM RoleEntity").getResultList();
    }

}
