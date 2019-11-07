package com.aarreaza.montask;

import com.aarreaza.montask.model.Account;
import com.aarreaza.montask.model.Statement;
import com.aarreaza.montask.service.RESTServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
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

        String rawOrigin = executeService("/Accounts/123456", "GET")[1];
        String rawDest = executeService("/Accounts/949751", "GET")[1];
        Statement statementOri = gson.fromJson(rawOrigin, statementToken.getType());
        Statement statementDes = gson.fromJson(rawDest, statementToken.getType());
        Account backupOrigin = statementOri.getAccount();
        Account backupDest = statementDes.getAccount();
        backupOrigin.deductBalance(amount);
        backupDest.addBalance(amount);

        System.out.println(executeService("/Accounts/" + amount + "/" + acc1 + "/" + acc2, "POST")[1]);

        String[] conn = executeService("/Accounts/123456", "GET");
        Statement statement = gson.fromJson(conn[1], statementToken.getType());

        System.out.println("New Statement: ");

        System.out.println(statement.toString());

        rawOrigin = executeService("/Accounts/123456", "GET")[1];
        rawDest = executeService("/Accounts/949751", "GET")[1];
        Statement statementOriResult = gson.fromJson(rawOrigin, statementToken.getType());
        Statement statementDesResult = gson.fromJson(rawDest, statementToken.getType());

        if(backupOrigin.getBalance().compareTo(statementOriResult.getAccount().getBalance()) == 0){
            System.out.println("Acount origin succesfully updated!");
        }
        if(backupDest.getBalance().compareTo(statementDesResult.getAccount().getBalance()) == 0){
            System.out.println("Acount des succesfully updated!");
        }


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
