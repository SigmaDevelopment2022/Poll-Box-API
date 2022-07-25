package com.sigma.pollboxapi.services;

import com.sigma.pollboxapi.mappers.AccountMapper;
import com.sigma.pollboxapi.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(accountRepository.findUsingEmail(username, new AccountMapper.Full()))
                .orElseThrow(() -> new UsernameNotFoundException("Não há usuário cadastrado com este e-mail."));
    }
}
