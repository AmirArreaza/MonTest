package com.aarreaza.montask.model.dao.comparator;

import com.aarreaza.montask.model.Account;

import java.util.Comparator;

public class AccountComparator implements Comparator<Account> {

    @Override
    public int compare(Account o1, Account o2) {
        if(o1.getNumber() > o2.getNumber()){
            return 1;
        }else if (o1.getNumber() == o2.getNumber()){
            return 0;
        }else{
            return -1;
        }
    }
}
