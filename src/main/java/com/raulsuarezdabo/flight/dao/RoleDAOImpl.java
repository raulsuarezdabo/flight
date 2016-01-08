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

import com.raulsuarezdabo.flight.entity.RoleEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

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
