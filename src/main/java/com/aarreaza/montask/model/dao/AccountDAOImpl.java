package com.aarreaza.montask.model.dao;

import com.aarreaza.montask.model.Account;
import com.aarreaza.montask.model.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.util.List;

@Component
public class AccountDAOImpl implements AccountDAO {

    private JdbcTemplate jdbcTemplate;

    private final String INSERT_ACCOUNT =   "insert into account (number, sortcode, currency, balance) " +
                                            " values (?,?,?,?)";

    private final String GET_ALL = "select * from account";

    private final String GET_BY_NUMBER = "select * from account where number = ?";

    private final String UPDATE_BALANCE = "update account set balance = ? where number = ?";

    @Autowired
    public AccountDAOImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public operationResult create(Account account) {
        int rowsAffected;
        try{
            rowsAffected = jdbcTemplate.update(INSERT_ACCOUNT,
                                    account.getNumber(),
                                    account.getSortCode(),
                                    account.getCurrency(),
                                    account.getBalance());

        }catch(DuplicateKeyException dkex){
            return operationResult.DUPLICATE_KEY;
        }
        if(rowsAffected == 1){
            return operationResult.SUCCESS;
        }else{
            return operationResult.UKNOWN;
        }
    }

    @Override
    public List<Account> getAll() {
        return jdbcTemplate.query(GET_ALL, new AccountMapper());
    }

    @Override
    public Account getById(int number) {
        try{
            return jdbcTemplate.queryForObject(GET_BY_NUMBER, new Object[] { number }, new AccountMapper());
        }catch(EmptyResultDataAccessException erdex){
            return null;
        }
    }

    @Override
    public operationResult update(Account object) {
        throw new NotImplementedException();
    }

    @Override
    public operationResult updateBalance(Account account) {
        int rowsAffected;
        try{
            rowsAffected = jdbcTemplate.update(UPDATE_BALANCE,
                            account.getBalance(),
                            account.getNumber());

        }catch(Exception ex){
            return operationResult.UKNOWN;
        }
        if(rowsAffected == 1){
            return operationResult.SUCCESS;
        }else{
            return operationResult.ERROR;
        }
    }

}
