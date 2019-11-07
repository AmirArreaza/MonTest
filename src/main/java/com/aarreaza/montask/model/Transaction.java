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

    /**
     * @param id
     */
    public Transaction(UUID id){
        this.id = id;
    }

    /**
     * @return
     */
    public UUID getId() {
        return id;
    }

    /**
     * @return
     */
    public Account getOrigin() {
        return origin;
    }

    /**
     * @param origin
     */
    public void setOrigin(Account origin) {
        this.origin = origin;
    }

    /**
     * @return
     */
    public Account getDestination() {
        return destination;
    }

    /**
     * @param destination
     */
    public void setDestination(Account destination) {
        this.destination = destination;
    }

    /**
     * @return
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return
     */
    public TxnResult getResult() {
        return result;
    }

    /**
     * @param result
     */
    public void setResult(TxnResult result) {
        this.result = result;
    }

    /**
     * @return
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }


    /**
     *
     */
    public void setTimestamp() {
        Date d = new Date();
        this.timestamp = new Timestamp(d.getTime());
    }

    /**
     * @param timestamp
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
