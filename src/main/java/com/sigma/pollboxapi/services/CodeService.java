package com.sigma.pollboxapi.services;

import com.sigma.pollboxapi.domain.Account;
import com.sigma.pollboxapi.domain.Code;
import com.sigma.pollboxapi.domain.CodeType;
import com.sigma.pollboxapi.repositories.AuthRepository;
import com.sigma.pollboxapi.repositories.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CodeService {
    private final CodeRepository codeRepository;
    private final AuthRepository authRepository;

    private void invalidateOldCodes(long accountId) {
        codeRepository.update(Code.builder().accountId(accountId).valid(false).build());
    }

    public long generateCode(String type) {
        CodeType codeType = CodeType.valueOf(type.toUpperCase(Locale.ROOT));
        Account loggedAccount = authRepository.getLoggedAccount();
        invalidateOldCodes(loggedAccount.getId());

        String randomCode = UUID.randomUUID().toString();
        boolean inserted = codeRepository.insert(Code.builder()
                .code(randomCode)
                .type(codeType)
                .accountId(loggedAccount.getId())
                .build());

        if (!inserted) {
            generateCode(type);
        }

        return codeRepository.findUsingCode(randomCode).getId();
    }
}
