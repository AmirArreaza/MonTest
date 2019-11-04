package com.aarreaza.montask.model;

import java.math.BigDecimal;

public class Account {

    private int number;

    private String sortCode;

    private String currency;

    private BigDecimal balance;

    public Account(int number){
        this.number = number;
    }

    /**
     * Number of the account
     */
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Bank sort code
     */
    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    /**
     * Account currency
     */
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Money balance in the account
     */
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
