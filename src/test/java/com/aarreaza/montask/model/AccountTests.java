package com.aarreaza.montask.model;

import com.aarreaza.montask.model.dao.AccountDAOImpl;
import org.junit.Assert;
import org.junit.Test;

public class AccountTests {


    @Test
    public void createAccount(){
        Account newAccount = new Account(123456);
        AccountDAOImpl accountImpl = new AccountDAOImpl();
        Assert.assertTrue(accountImpl.create(newAccount));
    }






}
