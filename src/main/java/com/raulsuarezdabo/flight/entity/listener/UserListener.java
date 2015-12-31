/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.entity.listener;

import com.raulsuarezdabo.flight.entity.UserEntity;
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * User listener for logical management for tracking
 * @author raulsuarez
 */
public class UserListener {
    
    @PrePersist
    public void preListener(UserEntity user) {
        user.setCreatedAt(new Date());
        user.setModifiedAt(new Date());
    }
    
    @PreUpdate
    public void updateListener(UserEntity user) {
        user.setModifiedAt(new Date());
    }
    
}
