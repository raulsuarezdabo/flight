package com.mycompany.flight.dao;

import com.raulsuarezdabo.flight.entity.RoleEntity;
import java.util.List;

/**
 *
 * @author raulsuarez
 */
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
