package com.aarreaza.montask.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class Transaction {

    public enum TxnResult{
        SUCCESS,
        INCOMPLETE,
        NOT_ENOUGH_FUNDS,
        UNKNOWN,
        FAILED,
        MISSING_ACCOUNT,
        BAD_PARAMETERS
    }

    private UUID id;

    private Account origin;

    private Account destination;

    private double amount;

    private TxnResult result;

    private Timestamp timestamp;

    public Transaction(){
        this.id = UUID.randomUUID();
    }

    public Transaction(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Account getOrigin() {
        return origin;
    }

    public void setOrigin(Account origin) {
        this.origin = origin;
    }

    public Account getDestination() {
        return destination;
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TxnResult getResult() {
        return result;
    }

    public void setResult(TxnResult result) {
        this.result = result;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        Date d = new Date();
        this.timestamp = new Timestamp(d.getTime());
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
