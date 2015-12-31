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
