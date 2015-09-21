
package com.raulsuarezdabo.flight.batch.configuration;

import javax.sql.DataSource;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author raulsuarez
 */
@Configuration
@EnableBatchProcessing
@PropertySource("classpath:/com/raulsuarezdabo/flight/database.properties")
public class StandaloneInfrastructureConfiguration  implements InfrastructureConfiguration {
    
    
    @Value("${datasource.url}")
    private String url;
    
    @Value("${datasource.driverClassName}")
    private String driverClassName;
    
    @Value("${datasource.username}")
    private String username;
    
    @Value("${datasource.password}")
    private String password;

    /**
     * Getter url
     * @return  String
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter url
     * @param url   String 
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Getter driverClassName
     * @return  String
     */
    public String getDriverClassName() {
        return driverClassName;
    }

    /**
     * Setter driverClassName
     * @param driverClassName   String 
     */
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    /**
     * Getter userName
     * @return  String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter userName
     * @param username  String 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter password
     * @return  String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter password
     * @param password  String 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    @Override
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(this.driverClassName);
        driverManagerDataSource.setUrl(this.url);
        driverManagerDataSource.setUsername(this.username);
        driverManagerDataSource.setPassword(this.password);
        return driverManagerDataSource;
    }
}
