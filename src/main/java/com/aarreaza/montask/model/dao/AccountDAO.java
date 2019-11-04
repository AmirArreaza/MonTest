package com.aarreaza.montask.model.dao;

import com.aarreaza.montask.model.Account;

import java.util.List;

public interface AccountDAO {

    enum operationResult {
        SUCCESS,
        DUPLICATE_KEY,
        UKNOWN,
        ERROR
    }

    operationResult create(Account account);

    List<Account> getAccounts();

    Account getAccount(int number);

    operationResult update(Account account);

}
