package com.aarreaza.montask.model.dao;

import com.aarreaza.montask.model.Transaction;

import java.util.List;

public interface TransactionDAO extends GenericDAO<Transaction> {

    List<Transaction> getByAccount(int origin);



}
