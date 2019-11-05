package com.aarreaza.montask.model;

import com.aarreaza.montask.config.AppConfig;
import com.aarreaza.montask.model.dao.AccountDAO;
import com.aarreaza.montask.model.dao.GenericDAO;
import com.aarreaza.montask.model.dao.TransactionDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.util.Date;

public class TransactionTests {


    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;

    @Before
    public void configTest(){
        this.transactionDAO = context.getBean(TransactionDAO.class);
        this.accountDAO = context.getBean(AccountDAO.class);
    }

    @Test
    public void createTransaction(){
        Account origin = accountDAO.getById(123456);
        Account dest = accountDAO.getById(193990);

        Date d = new Date();
        Timestamp t = new Timestamp(d.getTime());

        Transaction transaction = new Transaction();
        transaction.setAmount(50);
        transaction.setDestination(dest);
        transaction.setOrigin(origin);
        transaction.setTimestamp(t);
        transaction.setResult(Transaction.TxnResult.SUCCESS);
        Assert.assertEquals(GenericDAO.operationResult.SUCCESS, transactionDAO.create(transaction));
    }

}
