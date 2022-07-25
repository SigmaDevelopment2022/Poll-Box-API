package com.sigma.pollboxapi.services;

import com.sigma.pollboxapi.domain.Account;
import com.sigma.pollboxapi.exceptions.DuplicatedEntryException;
import com.sigma.pollboxapi.mappers.AccountMapper;
import com.sigma.pollboxapi.repositories.AccountRepository;
import com.sigma.pollboxapi.repositories.AuthRepository;
import com.sigma.pollboxapi.requests.AccountSignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;

    private void checkAlreadyExists(String email) {
        Account account = accountRepository.findUsingEmail(email, new AccountMapper.Full());
        if (account != null) {
            throw new DuplicatedEntryException("Este e-mail já está em uso.");
        }
    }

    public Account signup(AccountSignupRequest request) {
        checkAlreadyExists(request.getEmail());

        accountRepository.insert(Account.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .deviceIdentification(request.getDeviceIdentification())
                .build());

        return accountRepository.findUsingEmail(request.getEmail(), new AccountMapper.Safe());
    }

    public Account login(String deviceIdentification) {
        Account loggedAccount = authRepository.getLoggedAccount();
        loggedAccount.setDeviceIdentification(deviceIdentification);
        accountRepository.update(loggedAccount);
        return accountRepository.findUsingEmail(loggedAccount.getEmail(), new AccountMapper.Safe());
    }
}
