/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author raulsuarez
 */
@Entity
@Table(name = "Role")
public class RoleEntity implements GrantedAuthority {
    
    private static final long serialVersionUID = 1326777786L;
    /**
     * Predefined roles to the users
     */
    public static final String USER_ROLE = "USER_ROLE";
    public static final String ADMIN_ROLE = "ADMIN_ROLE";

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "Name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "UserRole",
            joinColumns = {
                @JoinColumn(name = "RoleID", referencedColumnName = "ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "UserID", referencedColumnName = "ID")})
    private List<UserEntity> userList;

    public RoleEntity() {
    }

    public RoleEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }

    /**
     * Method that returns Authority name (Spring security integration)
     * @return  String
     */
    @Override
    public String getAuthority() {
        return this.name;
    }

}
