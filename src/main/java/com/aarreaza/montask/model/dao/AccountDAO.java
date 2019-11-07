package com.aarreaza.montask.model.dao;

import com.aarreaza.montask.model.Account;

public interface AccountDAO extends GenericDAO<Account> {

    operationResult updateBalance(Account account);

}
