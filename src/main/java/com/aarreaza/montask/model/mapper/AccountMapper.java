package com.aarreaza.montask.model.mapper;

import com.aarreaza.montask.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account(resultSet.getInt("number"));
        account.setCurrency(resultSet.getString("currency"));
        account.setSortCode(resultSet.getString("sortcode"));
        account.addBalance(resultSet.getDouble("balance"));
        return account;
    }
}
