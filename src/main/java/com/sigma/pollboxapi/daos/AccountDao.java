package com.sigma.pollboxapi.daos;

import com.sigma.pollboxapi.domain.Account;
import com.sigma.pollboxapi.exceptions.DuplicatedEntryException;
import com.sigma.pollboxapi.exceptions.NotFoundException;
import com.sigma.pollboxapi.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountDao {
    private final JdbcTemplate template;

    public boolean insert(Account account) {
        String statement = "insert into accounts(username, email, password, device_identification) values (?,?,?,?)";
        try {
            return template.update(statement,
                    account.getUsername(),
                    account.getEmail(),
                    account.getPassword(),
                    account.getDeviceIdentification()) > 0;
        } catch (DataAccessException e) {
            if (e.getLocalizedMessage().contains("Duplicate")) {
                throw new DuplicatedEntryException("Este e-mail já está em uso.");
            }
            throw e;
        }
    }

    public Account findUsingEmail(String email, AccountMapper mapper) {
        String statement = "select * from accounts where email=?";
        try {
            return template.queryForObject(statement, mapper, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean update(Account account) {
        String statement = "update accounts set username=?, password=?, authenticated=?, device_identification=? where email=?";
        try {
            return template.update(statement,
                    account.getUsername(),
                    account.getPassword(),
                    account.isAuthenticated(),
                    account.getEmail()) > 0;
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Não há usuário cadastrado com este e-mail.");
        }
    }
}
