package com.raulsuarezdabo.flight.batch.configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author raulsuarez
 */
public interface InfrastructureConfiguration {

    @Bean
    public abstract DataSource dataSource();
}
