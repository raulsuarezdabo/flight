/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raulsuarezdabo.flight.batch.tasklet;

import com.raulsuarezdabo.flight.entity.BookEntity;
import com.raulsuarezdabo.flight.entity.FlightEntity;
import com.raulsuarezdabo.flight.entity.UserEntity;
import com.raulsuarezdabo.flight.service.BookService;
import com.raulsuarezdabo.flight.service.FlightService;
import java.util.Date;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author raulsuarez
 */
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
