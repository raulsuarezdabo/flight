
package com.raulsuarezdabo.flight.entity.listener;

import com.raulsuarezdabo.flight.entity.BookEntity;
import java.util.Date;
import javax.persistence.PrePersist;

/**
 * User listener for logical management for tracking
 * @author raulsuarez
 */
public class BookListener {
    
    @PrePersist
    public void preListener(BookEntity book) {
        book.setCreatedAt(new Date());
    }
    
}
