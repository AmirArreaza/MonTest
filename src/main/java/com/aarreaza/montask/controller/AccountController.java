package com.aarreaza.montask.controller;

import com.aarreaza.montask.model.Account;
import com.aarreaza.montask.model.Statement;
import com.aarreaza.montask.model.Transaction;
import com.aarreaza.montask.model.dao.AccountDAO;
import com.aarreaza.montask.model.dao.TransactionDAO;
import com.aarreaza.montask.model.dao.comparator.AccountComparator;
import com.aarreaza.montask.model.dao.comparator.TransactionComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AccountController {

    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private TransactionDAO transactionDAO;

    public Statement getAccountStatement(int accountNumber){
        Account account = accountDAO.getById(accountNumber);
        if(account == null){
            return null;
        }
        TreeSet<Transaction> sortedTransactions = new TreeSet<>(new TransactionComparator());

        List<Transaction> transactions = transactionDAO.getByAccount(account.getNumber());
        sortedTransactions.addAll(transactions);

        return new Statement(account, new ArrayList<>(sortedTransactions));
    }

    public TreeSet<Account> getAllAccounts(){
        TreeSet<Account> sortedAccounts = new TreeSet<>(new AccountComparator());
        List<Account> accounts = accountDAO.getAll();

        sortedAccounts.addAll(accounts);

        return sortedAccounts;
    }

    public Account getByNumber(int number) {
        return accountDAO.getById(number);
    }
}



