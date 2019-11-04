package com.aarreaza.montask.model.dao;

import com.aarreaza.montask.model.Account;

import java.util.List;

public interface AccountDAO extends GenericDAO<Account> {

    operationResult updateBalance(Account account);

}
