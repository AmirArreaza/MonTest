package com.aarreaza.montask.service;

import com.aarreaza.montask.config.AppConfig;
import com.aarreaza.montask.controller.AccountController;
import com.aarreaza.montask.controller.TransactionController;
import com.aarreaza.montask.model.Account;
import com.aarreaza.montask.model.Statement;
import com.aarreaza.montask.model.Transaction;
import com.google.gson.Gson;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import spark.Request;
import spark.Response;

import java.util.TreeSet;

import static spark.Spark.*;

@Service
public class RESTServer implements Runnable {

    private AnnotationConfigApplicationContext context;

    private AccountController accountController;
    private TransactionController transactionController;

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
        this.transactionController = context.getBean(TransactionController.class);
        init();
        loadServices();
    }

    /**
     *
     */
    private void loadServices(){
        get("/hello", (req, res) -> "Hello");
        get("/Accounts", (req, res) -> getAllAccountsService(req,res));
        get("/Accounts/:number", (req, res) -> getStatementService(req,res));
        post("/Accounts/:amount/:origin/:dest", (req, res) -> createTransaction(req, res));
    }

    /**
     * @param req
     * @param res
     * @return
     */
    private String getAllAccountsService(Request req, Response res) {
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

    /**
     * @param req
     * @param res
     * @return
     */
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

    /**
     * @param req
     * @param res
     * @return
     */
    private String createTransaction(Request req, Response res) {
        try{
            double amount = Double.parseDouble(req.params("amount"));
            int origin = Integer.parseInt(req.params("origin"));
            int destination = Integer.parseInt(req.params("dest"));
            Transaction.TxnResult result = transactionController.sendMoney(amount, origin, destination);

            res.status(200);
            return result.toString();
        }catch (NumberFormatException numex){
            res.status(400);
            return Transaction.TxnResult.BAD_PARAMETERS.toString();
        }catch (Exception ex){
            res.status(500);
            return Transaction.TxnResult.UNKNOWN.toString();
        }
    }

}
