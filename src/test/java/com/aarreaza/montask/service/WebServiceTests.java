package com.aarreaza.montask.service;

import com.aarreaza.montask.model.Account;
import com.aarreaza.montask.model.Statement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class WebServiceTests {
    private RESTServer server;
    private GsonBuilder gsonBilder;
    private Gson gson;

    private TypeToken<List<Account>> accountToken;
    private TypeToken<Statement> statementToken;

    @Before
    public void configTest(){
        this.server = new RESTServer();
        server.run();
        gsonBilder = new GsonBuilder();
        //gsonBilder.registerTypeAdapter(Account.class, new AccountAdapter());
        gson = gsonBilder.create();
        accountToken = new TypeToken<List<Account>>(){};
        statementToken = new TypeToken<Statement>(){};
    }

    @Test
    public void displayAccounts(){
        String[] conn =executeService("/Accounts", "GET");
        Assert.assertEquals(conn[0], "200");
        List<Account> accounts = gson.fromJson(conn[1], accountToken.getType());
        accounts.forEach(acc -> System.out.println(acc.toString()));
    }

    @Test
    public void getStatement(){
        String[] conn =executeService("/Accounts/123456", "GET");
        Assert.assertEquals(conn[0], "200");

        System.out.println(conn[1]);

        Statement statement = gson.fromJson(conn[1], statementToken.getType());
        System.out.println(statement.toString());
    }

    @Test
    public void makeTransaction(){
        double amount = 10.1;
        int acc1 = 123456;
        int acc2 = 949751;

        String[] conn =executeService("/Accounts/" + amount + "/" + acc1 + "/" + acc2, "POST");
        Assert.assertEquals(conn[0], "200");
        Assert.assertEquals(conn[1], "SUCCESS");

    }

    private String[] executeService(String route, String method){
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
