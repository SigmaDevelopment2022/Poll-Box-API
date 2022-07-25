package com.sigma.pollboxapi.repositories;

import com.sigma.pollboxapi.daos.CodeDao;
import com.sigma.pollboxapi.domain.Code;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CodeRepository {
    private final CodeDao codeDao;

    public boolean insert(Code code) {
        return codeDao.insert(code);
    }

    public Code findUsingCode(String code) {
        return codeDao.findUsingCode(code);
    }

    public boolean update(Code code) {
        return codeDao.update(code);
    }
}
