
package com.raulsuarezdabo.flight.entity.listener;

import com.raulsuarezdabo.flight.entity.SeatEntity;
import java.util.Date;
import javax.persistence.PrePersist;

/**
 * User listener for logical management for tracking
 * @author raulsuarez
 */
public class SeatListener {
    
    @PrePersist
    public void preListener(SeatEntity seat) {
        seat.setCreatedAt(new Date());
    }
    
}
