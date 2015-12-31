/**
 * 
 * This file is part of the Master Java specialization for Atrium Final Project.
 *
 * @author Raul Suarez Dabo <raul@suarez.com.es>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
package com.raulsuarezdabo.flight.batch.tasklet;

import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.service.BookService;
import com.raulsuarezdabo.flight.service.FlightService;
import java.util.Date;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class ReminderFlightTasklet implements Tasklet {
    
    @Autowired
    private FlightService flightService;
    
    @Autowired
    private BookService bookService;
      
    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
            throws Exception {
        Date now = new Date();
        
        for(FlightEntity flight: this.flightService.getTakeOffByDate(now)) {
            for (BookEntity book: this.bookService.getBooks(flight)) {
                this.bookService.notifyIncommingFlight(book);
                
            }
        }
        return RepeatStatus.FINISHED;
    }
}
