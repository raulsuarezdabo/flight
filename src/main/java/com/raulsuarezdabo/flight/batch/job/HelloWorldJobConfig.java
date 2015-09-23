package com.raulsuarezdabo.flight.batch.job;

import com.raulsuarezdabo.flight.batch.configuration.InfrastructureConfiguration;
import com.raulsuarezdabo.flight.batch.tasket.HelloWorldTasklet;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.raulsuarezdabo.flight.batch.configuration.StandaloneInfrastructureConfiguration;

/**
 *
 * @author raulsuarez
 */
@Configuration
@Import(StandaloneInfrastructureConfiguration.class)
public class HelloWorldJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilders;
     
    @Autowired
    private StepBuilderFactory stepBuilders;
     
    @Autowired
    private InfrastructureConfiguration infrastructureConfiguration;
     
    @Autowired
    private DataSource dataSource;
    
    public JobBuilderFactory getJobBuilders() {
        return jobBuilders;
    }

    public void setJobBuilders(JobBuilderFactory jobBuilders) {
        this.jobBuilders = jobBuilders;
    }

    public StepBuilderFactory getStepBuilders() {
        return stepBuilders;
    }

    public void setStepBuilders(StepBuilderFactory stepBuilders) {
        this.stepBuilders = stepBuilders;
    }

    public InfrastructureConfiguration getInfrastructureConfiguration() {
        return infrastructureConfiguration;
    }

    public void setInfrastructureConfiguration(InfrastructureConfiguration infrastructureConfiguration) {
        this.infrastructureConfiguration = infrastructureConfiguration;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
     
    @Bean
    public Job helloWorldJob(){
        return jobBuilders.get("helloWorldJob")
                .start(step())
                .build();
    }
     
    @Bean
    public Step step(){
        return stepBuilders.get("step")
                .tasklet(tasklet())
                .build();
    }
     
    @Bean
    public Tasklet tasklet() {
        return new HelloWorldTasklet();
    }
}
