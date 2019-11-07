package com.aarreaza.montask.model.dao;

import com.aarreaza.montask.model.Transaction;
import com.aarreaza.montask.model.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class TransactionDAOImpl implements TransactionDAO {

    private JdbcTemplate jdbcTemplate;

    private final String INSERT_TRANSACTION =   "insert into transaction (id, origin, destination, amount, result, date) " +
                                            " values (?,?,?,?,?,?)";

    private final String GET_BY_ACCOUNT = "select * from transaction where origin = ?";

    /**
     * @param dataSource
     */
    @Autowired
    public TransactionDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * @param transaction
     * @return
     */
    @Override
    public operationResult create(Transaction transaction) {
        int rowsAffected;
        try{
            rowsAffected = jdbcTemplate.update(INSERT_TRANSACTION,
                    transaction.getId(),
                    transaction.getOrigin().getNumber(),
                    transaction.getDestination().getNumber(),
                    transaction.getAmount(),
                    transaction.getResult().toString(),
                    transaction.getTimestamp());

        }catch(DuplicateKeyException dkex){
            return operationResult.DUPLICATE_KEY;
        }
        if(rowsAffected == 1){
            return operationResult.SUCCESS;
        }else{
            return operationResult.UKNOWN;
        }
    }

    /**
     * @return
     */
    @Override
    public List<Transaction> getAll() {
        return null;
    }

    /**
     * @param number
     * @return
     */
    @Override
    public Transaction getById(int number) {
        return null;
    }

    /**
     * @param origin
     * @return
     */
    public List<Transaction> getByAccount(int origin){
        try{
            return jdbcTemplate.query(GET_BY_ACCOUNT, new Object[] { origin }, new TransactionMapper());
        }catch(EmptyResultDataAccessException erdex){
            return null;
        }
    }

    /**
     * @param object
     * @return
     */
    @Override
    public operationResult update(Transaction object) {
        return null;
    }
}
