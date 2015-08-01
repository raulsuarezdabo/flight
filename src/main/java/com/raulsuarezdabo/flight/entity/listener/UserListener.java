
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
