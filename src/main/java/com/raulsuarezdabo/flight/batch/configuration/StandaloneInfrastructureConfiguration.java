
package com.raulsuarezdabo.flight.batch.configuration;

import javax.sql.DataSource;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author raulsuarez
 */
@Configuration
@EnableBatchProcessing
public class StandaloneInfrastructureConfiguration  implements InfrastructureConfiguration {
    
    @Bean
    @Override
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/flight");
        driverManagerDataSource.setUsername("flight");
        driverManagerDataSource.setPassword("password");
        return driverManagerDataSource;
    }
    
}
