package com.aarreaza.montask.model;

import java.util.List;
import java.util.PriorityQueue;

public class Statement {

    private Account account;

    private List<Transaction> transactions;

    public Statement(Account account, List<Transaction> transactions){
        this.account = account;
        this.transactions = transactions;
    }

    public Account getAccount() {
        return account;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString(){

        String transactions = "";

        for(Transaction txn : this.transactions){
            transactions = transactions.concat(txn.getId() + " " + txn.getTimestamp() + " To:" + txn.getDestination().getNumber() + " "
                     + txn.getAmount() + "£\n");
        }
        return "Account " + this.account.getNumber() + " Current Balance " + this.account.getBalance() + "£\n"
                + transactions;

    }
}
