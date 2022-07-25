package com.sigma.pollboxapi.controllers;

import com.sigma.pollboxapi.domain.Account;
import com.sigma.pollboxapi.requests.AccountSignupRequest;
import com.sigma.pollboxapi.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("accounts")
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<Account> signup(@RequestBody @Validated AccountSignupRequest request) {
        return new ResponseEntity<>(accountService.signup(request), HttpStatus.CREATED);
    }
}
