/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.mycompany.flight.dao;

import com.raulsuarezdabo.flight.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

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
     * Method to add new user on DB
     *
     * @param user
     */
    @Override
    public void addUser(UserEntity user) {
        this.entityManager.persist(user);
    }

    /**
     * Method for updating the user entity
     * @param user
     * @return boolean  success or not flag
     */
    @Override
    public boolean updateUser(UserEntity user) {
        try {
            this.entityManager.merge(user);
            return true;
        } catch ( Exception e ) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Method to obtain all user from DB
     *
     * @return
     */
    @Override
    public List<UserEntity> getAllUsers() {
        return this.entityManager.createQuery("from UserEntity").getResultList();
    }

    /**
     * Method to delete the user entity
     *
     * @param user
     */
    @Override
    public boolean deleteUser(UserEntity user) {
        try {
            this.entityManager.remove(user);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Method to find the user by ID
     *
     * @param id
     * @return
     */
    @Override
    public UserEntity findById(Integer id) {
        try {
            return (UserEntity) this.entityManager.find(UserEntity.class, id);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method to find the user by email
     *
     * @param email
     * @return
     */
    @Override
    public UserEntity findByEmail(String email) {
        try {
            UserEntity user;
            Query query = this.entityManager.createQuery("FROM UserEntity  WhERE email = :email ");
            query.setParameter("email", email);
            user = (UserEntity) query.getSingleResult();
            return user;
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Method to find list of user for tracking
     * @param days  int
     * @return  List
     */
    @Override
    public List findCountUsersByDate(int days) {
        try {
            Query query = this.entityManager.createQuery("SELECT count(u.id), u.createdAt "
                    + "FROM UserEntity u "
                    + "WHERE u.createdAt < current_date() "
                    + "GROUP BY day(u.createdAt), month(u.createdAt), year(u.createdAt)"
                    + "ORDER BY u.createdAt DESC"
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
}
