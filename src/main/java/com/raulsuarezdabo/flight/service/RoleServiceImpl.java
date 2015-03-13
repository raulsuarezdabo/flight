package com.raulsuarezdabo.flight.service;

import com.mycompany.flight.dao.RoleDAO;
import com.raulsuarezdabo.flight.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class that implements the interface methods defined on it
 * @author raulsuarez
 */
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public RoleEntity getUserRole() {
        return this.roleDAO.findByName(RoleEntity.USER_ROLE);
    }

    @Override
    public RoleEntity getAdminRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RoleEntity getFlightRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
