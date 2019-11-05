package com.aarreaza.montask.model;

import java.util.List;
import java.util.PriorityQueue;

public class Balance {

    private Account account;

    private PriorityQueue<Transaction> transactions;

    public Balance(Account account, PriorityQueue<Transaction> transactions){
        this.account = account;
        this.transactions = transactions;
    }

    public Account getAccount() {
        return account;
    }

    public PriorityQueue<Transaction> getTransactions() {
        return transactions;
    }
}
