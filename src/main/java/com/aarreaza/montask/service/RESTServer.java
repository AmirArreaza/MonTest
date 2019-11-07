package com.aarreaza.montask.service;

import com.aarreaza.montask.config.AppConfig;
import com.aarreaza.montask.controller.AccountController;
import com.aarreaza.montask.model.Account;
import com.aarreaza.montask.model.Statement;
import com.aarreaza.montask.model.dao.comparator.AccountComparator;
import com.google.gson.Gson;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import spark.Request;
import spark.Response;


import java.util.PriorityQueue;
import java.util.TreeSet;

import static spark.Spark.get;
import static spark.Spark.init;

@Service
public class RESTServer implements Runnable {

    private AnnotationConfigApplicationContext context;

    private AccountController accountController;

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public void run() {
        this.context = new AnnotationConfigApplicationContext(AppConfig.class);
        this.accountController = context.getBean(AccountController.class);
        init();
        loadServices();
    }

    private void loadServices(){
        get("/hello", (req, res) -> "Hello");
        get("/Accounts", (req, res) -> getAccountsService(req,res));
        get("/Accounts/:number", (req, res) -> getStatementService(req,res));
    }

    private String getStatementService(Request req, Response res) {
        Statement statement;
        try{
            int accountNumber = Integer.parseInt(req.params("number"));
            statement = accountController.getAccountStatement(accountNumber);
            if(statement == null){
                res.status(404);//Account not found
            }
            return new Gson().toJson(statement);
        }catch (NumberFormatException nfex){
            //Validate Parameter
            res.status(400);
            return new Gson().toJson("");
        }catch (Exception ex) {
            res.status(500);
            return new Gson().toJson("");
        }
    }

    private String getAccountsService(Request req, Response res) {
        TreeSet<Account> accounts;
        try{
            accounts = accountController.getAllAccounts();
            if(accounts.size() < 1){
                res.status(204);
            }
            return new Gson().toJson(accounts);
        }catch (Exception ex){
            res.status(500);
            return new Gson().toJson("");
        }
    }


}
