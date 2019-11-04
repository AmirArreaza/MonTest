package com.aarreaza.montask;

import com.aarreaza.montask.service.RESTServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class Main {

    public  static void main(String[] args){
        RESTServer server = new RESTServer();
        server.run();

    }
}
