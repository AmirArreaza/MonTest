package com.aarreaza.montask.model;

import com.aarreaza.montask.config.AppConfig;
import com.aarreaza.montask.model.dao.AccountDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;

public class AccountTests {

    private AnnotationConfigApplicationContext context;
    private AccountDAO accountDAO;
    private static final Random generator = new Random();

    @Before
    public void configTest(){
         this.context = new AnnotationConfigApplicationContext(AppConfig.class);
         this.accountDAO = context.getBean(AccountDAO.class);


    }

    @Test
    public void createAccount(){

        int num = generator.nextInt(900000) + 100000;

        Account newAccount = new Account(num);
        newAccount.setSortCode("00-00-00");
        newAccount.setBalance(new BigDecimal(0));
        newAccount.setCurrency("GBP");

        Assert.assertEquals(AccountDAO.createResult.SUCCESS, accountDAO.create(newAccount));

        Account accountInDB = new Account(123456);
        newAccount.setSortCode("00-00-00");
        newAccount.setBalance(new BigDecimal(0));
        newAccount.setCurrency("GBP");

        Assert.assertEquals(AccountDAO.createResult.DUPLICATE_KEY, accountDAO.create(newAccount));
    }

    @Test
    public void listAllAccounts(){

        List<Account> accounts = accountDAO.getAccounts();

        Assert.assertTrue(accounts.size() > 0);

        for(Account acc : accounts){
            System.out.println("Account number: " + acc.toString());
        }
    }





}
