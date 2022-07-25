package com.sigma.pollboxapi.repositories;

import com.sigma.pollboxapi.daos.AccountDao;
import com.sigma.pollboxapi.domain.Account;
import com.sigma.pollboxapi.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountRepository {
    private final AccountDao accountDao;

    public boolean insert(Account account) {
        return accountDao.insert(account);
    }

    public Account findUsingEmail(String email, AccountMapper mapper) {
        return accountDao.findUsingEmail(email, mapper);
    }

    public boolean update(Account account) {
        return accountDao.update(account);
    }
}
