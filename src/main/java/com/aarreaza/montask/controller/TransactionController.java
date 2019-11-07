package com.aarreaza.montask.controller;

import com.aarreaza.montask.model.Account;
import com.aarreaza.montask.model.Transaction;
import com.aarreaza.montask.model.dao.AccountDAO;
import com.aarreaza.montask.model.dao.GenericDAO;
import com.aarreaza.montask.model.dao.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionController {

    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private TransactionDAO transactionDAO;

    public synchronized Transaction.TxnResult sendMoney(double amount, int origin, int dest){
        // Getting origin account to check on balance
        Account originAcc = accountDAO.getById(origin);
        Account destAcc = accountDAO.getById(dest);
        if(originAcc != null && destAcc != null){
            BigDecimal amountBD = new BigDecimal(amount);
            if(originAcc.getBalance().compareTo(amountBD) >= 0){
                return performTransaction(amount, originAcc, destAcc);
            }else{
                return Transaction.TxnResult.NOT_ENOUGH_FUNDS;
            }
        }else{
            return Transaction.TxnResult.MISSING_ACCOUNT;
        }
    }

    private Transaction.TxnResult performTransaction(double amount, Account originAcc, Account destAcc) {
        //Create the Transaction object
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(amount);
        newTransaction.setOrigin(originAcc);
        newTransaction.setDestination(destAcc);
        newTransaction.setTimestamp();
        //Take money from origin account
        BigDecimal backupBalance = originAcc.getBalance();
        originAcc.deductBalance(amount);
        if(accountDAO.updateBalance(originAcc) == GenericDAO.operationResult.SUCCESS){
            //Origin Account decreased succesfully
            destAcc.addBalance(amount);
            if(accountDAO.updateBalance(destAcc) == GenericDAO.operationResult.SUCCESS){
                //Destination Account incremented succesfully
                newTransaction.setResult(Transaction.TxnResult.SUCCESS);
            }else{
                //Issue while increasing balance on destination account
                //we need to reverse the balance decreased in the origin account
                originAcc.addBalance(amount);
                if(accountDAO.updateBalance(destAcc) == GenericDAO.operationResult.SUCCESS){
                    newTransaction.setResult(Transaction.TxnResult.INCOMPLETE);
                }
            }
        }
        if(transactionDAO.create(newTransaction) == GenericDAO.operationResult.SUCCESS){
            return newTransaction.getResult();
        }else{
            return Transaction.TxnResult.FAILED;
        }
    }

}
