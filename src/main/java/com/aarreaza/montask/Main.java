package com.aarreaza.montask;

import com.aarreaza.montask.model.Statement;
import com.aarreaza.montask.service.RESTServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main{

    public  static void main(String[] args){
        RESTServer server = new RESTServer();
        server.run();
        try{
            Thread.sleep(5000);
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }

        /**
         * Here a running end to end test to transfer money
         */
        GsonBuilder gsonBilder = new GsonBuilder();
        Gson gson = gsonBilder.create();
        TypeToken<Statement> statementToken = new TypeToken<Statement>(){};;

        double amount = 10.1;
        int acc1 = 123456;
        int acc2 = 949751;

        String[] conn = executeService("/Accounts/" + amount + "/" + acc1 + "/" + acc2, "POST");

        conn = executeService("/Accounts/123456", "GET");
        Statement statement = gson.fromJson(conn[1], statementToken.getType());

        System.out.println("New Statement: ");

        System.out.println(statement.toString());

    }

    private static String[] executeService(String route, String method){
        String[] response = new String[2];
        HttpURLConnection connection;
        try {
            URL url = new URL("http://localhost:4567" + route);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            response[0] = String.valueOf(connection.getResponseCode());
            if(response[0].equals("200")){
                response[1] = IOUtils.toString(connection.getInputStream());
            }else{
                response[1] = "Error";
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return new String[2];
        }
    }

}
