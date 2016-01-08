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

import com.raulsuarezdabo.flight.entity.RoleEntity;
import java.util.List;

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
    
    /**
     * Method for getting all roles available
     * @return List of Roles
     */
    public List<RoleEntity> getAll();
}
