package com.aarreaza.montask.model.mapper;

import com.aarreaza.montask.model.Account;
import com.aarreaza.montask.model.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TransactionMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        Transaction transaction = new Transaction(id);
        transaction.setOrigin(new Account(resultSet.getInt("origin")));
        transaction.setDestination(new Account(resultSet.getInt("destination")));
        transaction.setResult(Transaction.TxnResult.valueOf(resultSet.getString("result")));
        transaction.setAmount(resultSet.getInt("amount"));
        transaction.setTimestamp(resultSet.getTimestamp("date"));
        return transaction;
    }
}
