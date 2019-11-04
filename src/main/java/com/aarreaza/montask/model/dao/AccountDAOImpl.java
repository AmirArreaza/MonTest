package com.aarreaza.montask.model.dao;

import com.aarreaza.montask.model.Account;
import com.aarreaza.montask.model.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class AccountDAOImpl implements AccountDAO {

    private JdbcTemplate jdbcTemplate;

    private final String INSERT_ACCOUNT = "insert into account (number, sortcode, currency, balance) " +
            "                                                  values (?,?,?,?)";

    private final String GET_ALL = "select * from account";

    @Autowired
    public AccountDAOImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public createResult create(Account account) {
        int rowsAffected;
        try{
            rowsAffected = jdbcTemplate.update(INSERT_ACCOUNT,
                                    account.getNumber(),
                                    account.getSortCode(),
                                    account.getCurrency(),
                                    account.getBalance());

        }catch(DuplicateKeyException dkex){
            return createResult.DUPLICATE_KEY;
        }
        if(rowsAffected == 1){
            return createResult.SUCCESS;
        }else{
            return createResult.UKNOWN;
        }
    }

    @Override
    public List<Account> getAccounts() {
        return jdbcTemplate.query(GET_ALL, new AccountMapper());
    }
}
