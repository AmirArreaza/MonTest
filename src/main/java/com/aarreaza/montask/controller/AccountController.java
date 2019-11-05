package com.aarreaza.montask.controller;

import com.aarreaza.montask.model.Account;
import com.aarreaza.montask.model.Statement;
import com.aarreaza.montask.model.Transaction;
import com.aarreaza.montask.model.dao.AccountDAO;
import com.aarreaza.montask.model.dao.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Component
public class AccountController {

    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private TransactionDAO transactionDAO;

    public Statement getAccountStatement(int accountNumber){
        Account account = accountDAO.getById(accountNumber);
        List<Transaction> unsortedTransactions = transactionDAO.getByAccount(account.getNumber());
        PriorityQueue<Transaction> transactions = new PriorityQueue<>(new TransactionComparator());

        transactions.addAll(unsortedTransactions);
        return new Statement(account, transactions);
    }

    public PriorityQueue<Account> getAllAccounts(){
        PriorityQueue<Account> accounts = new PriorityQueue<>(new AccountComparator());
        List<Account> unsortedAccounts = accountDAO.getAll();

        accounts.addAll(unsortedAccounts);
        return accounts;
    }

}

class AccountComparator implements Comparator<Account>{

    @Override
    public int compare(Account o1, Account o2) {
        if(o1.getNumber() > o2.getNumber()){
            return 1;
        }else if (o1.getNumber() == o2.getNumber()){
            return 0;
        }else{
            return -1;
        }
    }
}

class TransactionComparator implements Comparator<Transaction>{

    @Override
    public int compare(Transaction o1, Transaction o2) {
        return o1.getTimestamp().compareTo(o2.getTimestamp());
    }
}