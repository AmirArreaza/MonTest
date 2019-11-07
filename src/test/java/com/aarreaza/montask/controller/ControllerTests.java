package com.aarreaza.montask.controller;

import com.aarreaza.montask.config.AppConfig;
import com.aarreaza.montask.model.Account;
import com.aarreaza.montask.model.Statement;
import com.aarreaza.montask.model.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class ControllerTests {

    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    private AccountController accountController;
    private TransactionController transactionController;

    @Before
    public void configTests(){
        this.accountController = context.getBean(AccountController.class);
        this.transactionController = context.getBean(TransactionController.class);
    }

    @Test
    public void displayAccounts(){
        TreeSet<Account> accounts = accountController.getAllAccounts();
        Assert.assertTrue(accounts.size() > 0);
        while(!accounts.isEmpty()){
            System.out.println(accounts.pollFirst());
        }
    }

    @Test
    public void displayStatement(){
        Statement statement = accountController.getAccountStatement(123456);

        Assert.assertNotNull(statement);
        System.out.println(statement.toString());
    }

    @Test
    public void sendMoneySuccess(){
        double amount = 10.1;
        int acc1 = 123456;
        int acc2 = 949751;

        Account expectedOrigin = accountController.getByNumber(acc1);
        expectedOrigin.deductBalance(amount);
        Account expectedDest = accountController.getByNumber(acc2);
        expectedDest.addBalance(amount);

        Transaction.TxnResult result = transactionController.sendMoney(amount, acc1, acc2);

        Assert.assertEquals(Transaction.TxnResult.SUCCESS, result);

        Account resultOrigin = accountController.getByNumber(acc1);
        Account resultDest = accountController.getByNumber(acc2);

        Assert.assertEquals(expectedOrigin.getBalance(), resultOrigin.getBalance());
        Assert.assertEquals(expectedDest.getBalance(), resultDest.getBalance());
    }

    @Test
    public void sendMoneyNotEnoughFunds(){
        double amount = 10000;
        int acc1 = 949751;
        int acc2 = 123456;

        Transaction.TxnResult result = transactionController.sendMoney(amount, acc1, acc2);

        Assert.assertEquals(Transaction.TxnResult.NOT_ENOUGH_FUNDS, result);

    }

    @Test
    public void sendMoneyError(){
        double amount = 10000;
        int acc1 = 949751;
        int acc2 = 12456;

        Transaction.TxnResult result = transactionController.sendMoney(amount, acc1, acc2);

        Assert.assertEquals(Transaction.TxnResult.MISSING_ACCOUNT, result);

    }

    @Test
    public void sendMoneyThreadSafe(){
        double amount = 10;
        int acc1 = 119840;
        int acc2 = 155469;

        IntStream.range(0, 20).parallel().forEach(
                nbr -> {
                    Transaction.TxnResult result = transactionController.sendMoney(amount, acc1, acc2);
                    Assert.assertEquals(Transaction.TxnResult.SUCCESS, result);
                }
        );

    }

}
