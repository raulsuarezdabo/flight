package com.mycompany.flight.dao;

import com.raulsuarezdabo.flight.entity.RoleEntity;

/**
 *
 * @author raulsuarez
 */
public interface RoleDAO {
    /**
     * Find by id on the user table
     *
     * @param id
     * @return UserEntity with the param asociated or null
     */
    public RoleEntity findById(Integer id);

}
