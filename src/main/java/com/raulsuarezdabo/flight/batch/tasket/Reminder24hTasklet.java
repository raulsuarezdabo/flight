
package com.raulsuarezdabo.flight.batch.tasket;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 *
 * @author raulsuarez
 */
public class Reminder24hTasklet implements Tasklet {
      
    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
            throws Exception {
        return RepeatStatus.FINISHED;
    }
}
