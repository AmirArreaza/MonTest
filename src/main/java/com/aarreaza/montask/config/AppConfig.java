package com.aarreaza.montask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan("com.aarreaza.montask")
@PropertySource("classpath:database.properties")
public class AppConfig {

    @Autowired
    private Environment enviroment;

    @Bean
    DataSource dataSource(){
        DriverManagerDataSource driverMDS  = new DriverManagerDataSource();
        driverMDS.setUrl(enviroment.getProperty("url"));
        driverMDS.setDriverClassName(Objects.requireNonNull(enviroment.getProperty("driver")));
        driverMDS.setUsername(enviroment.getProperty("user"));
        driverMDS.setPassword(enviroment.getProperty("pass"));
        return driverMDS;
    }
}
