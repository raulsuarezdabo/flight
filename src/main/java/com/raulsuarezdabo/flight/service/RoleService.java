package com.raulsuarezdabo.flight.service;

import com.raulsuarezdabo.flight.entity.RoleEntity;

/**
 * Interface that defines the methods and functinality for the implementation
 * @author raulsuarez
 */
public interface RoleService {
    /**
     * Gets the RoleEntity that correspons to user Role
     * @return 
     */
    RoleEntity getUserRole();
    
    /**
     * Gets the RoleEntity that corresponse to a admin Role
     * @return 
     */
    RoleEntity getAdminRole();
    
    /**
     * Gets the RoleEntity that corresponse to an administrative Role
     * @return 
     */
    RoleEntity getFlightRole();
}
