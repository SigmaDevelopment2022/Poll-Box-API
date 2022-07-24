package com.sigma.pollboxapi.daos;

import com.sigma.pollboxapi.domain.Account;
import com.sigma.pollboxapi.exceptions.DuplicatedEntryException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
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
}
