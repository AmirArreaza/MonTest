package com.aarreaza.montask.model.dao;

import com.aarreaza.montask.model.Account;

public interface AccountDAO {

    enum createResult{
        SUCCESS,
        DUPLICATE_KEY,
        UKNOWN
    }

    createResult create(Account account);

}
