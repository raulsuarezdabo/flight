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

import com.raulsuarezdabo.flight.entity.RoleEntity;
import java.util.List;

public interface RoleDAO {
    /**
     * Find by id on the user table
     *
     * @param name
     * @return UserEntity with the param asociated or null
     */
    public RoleEntity findByName(String name);

    public List<RoleEntity> findAll();
}
