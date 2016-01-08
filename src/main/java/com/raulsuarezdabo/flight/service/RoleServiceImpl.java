/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.dao.RoleDAO;
import com.raulsuarezdabo.flight.entity.RoleEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class that implements the interface methods defined on it
 * @author raulsuarez
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleDAO roleDAO;

    /**
     * Method for getting the user Role
     * @return  RoleEntity users one
     */
    @Override
    public RoleEntity getUserRole() {
        return this.roleDAO.findByName(RoleEntity.USER_ROLE);
    }
    
    /**
     * Method for getting all types of roles available
     * @return  List of roles
     */
    @Override
    public List<RoleEntity> getAll() {
        return this.roleDAO.findAll();
    }

    /**
     * Method for getting the admin role
     * @return  RoleEntity admins one
     */
    @Override
    public RoleEntity getAdminRole() {
        return this.roleDAO.findByName(RoleEntity.ADMIN_ROLE);
    }

    @Override
    public RoleEntity getFlightRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
