package com.aarreaza.montask.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.aarreaza.montask")
@PropertySource("classpath:database.properties")
public class AppConfig {

    @Autowired
    Environment enviroment;

    @Bean
    DataSource dataSource(){
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setUrl("url");
        driver.setDriverClassName("driver");
        driver.setUsername("user");
        driver.setPassword("pass");
        return driver;
    }
}
