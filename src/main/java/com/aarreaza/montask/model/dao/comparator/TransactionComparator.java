package com.aarreaza.montask.model.dao.comparator;

import com.aarreaza.montask.model.Transaction;

import java.util.Comparator;

public class TransactionComparator implements Comparator<Transaction> {

    /**
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Transaction o1, Transaction o2) {
        return o1.getTimestamp().compareTo(o2.getTimestamp());
    }
}