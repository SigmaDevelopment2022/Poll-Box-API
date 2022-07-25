package com.sigma.pollboxapi.controllers;

import com.sigma.pollboxapi.domain.Account;
import com.sigma.pollboxapi.requests.AccountSignupRequest;
import com.sigma.pollboxapi.services.AccountService;
import com.sigma.pollboxapi.services.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping()
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final CodeService codeService;

    @PostMapping("accounts/signup")
    public ResponseEntity<Account> signup(@RequestBody @Validated AccountSignupRequest request) {
        return new ResponseEntity<>(accountService.signup(request), HttpStatus.CREATED);
    }

    @PutMapping("admin/accounts/login")
    public ResponseEntity<Account> login(@RequestParam(value = "device-identification") String deviceIdentification) {
        return new ResponseEntity<>(accountService.login(deviceIdentification), HttpStatus.OK);
    }

    @PostMapping("admin/accounts/generate-code")
    public ResponseEntity<Long> generateCode(@RequestParam String type) {
        return new ResponseEntity<>(codeService.generateCode(type), HttpStatus.CREATED);
    }
}
