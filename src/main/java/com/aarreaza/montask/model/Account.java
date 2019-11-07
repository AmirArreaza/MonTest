package com.aarreaza.montask.model;

import java.math.BigDecimal;

public class Account {

    private int number;

    private String sortCode;

    private String currency;

    private BigDecimal balance = new BigDecimal(0);

    public Account(int number){
        this.number = number;
    }

    /**
     * Number of the account
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     * Bank sort code
     * @return
     */
    public String getSortCode() {
        return sortCode;
    }

    /**
     * @param sortCode
     */
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    /**
     * Account currency
     * @return
     */
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Money balance in the account
     * @return
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     *
     * @param amount to add to the balance
     */
    public void addBalance(double amount) {
        this.balance = this.balance.add(BigDecimal.valueOf(amount).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    }

    /**
     *
     * @param amount to deduct from the balance
     */
    public void deductBalance(double amount) {
        this.balance = this.balance.add(BigDecimal.valueOf((-1) * amount).setScale(2,BigDecimal.ROUND_HALF_EVEN));
    }

    /**
     * @return
     */
    @Override
    public String toString(){
        return "Account Number " + this.number + " " +
                " Sort Code " + this.getSortCode() +
                " Current Balance = " + this.getBalance();
    }
}
