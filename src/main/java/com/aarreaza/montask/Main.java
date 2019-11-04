package com.aarreaza.montask;

import com.aarreaza.montask.service.RESTServer;

public class Main {

    public  static void main(String[] args){
        RESTServer server = new RESTServer();
        server.run();
    }
}
