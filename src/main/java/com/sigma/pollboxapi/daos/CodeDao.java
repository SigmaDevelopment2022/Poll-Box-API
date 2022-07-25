package com.sigma.pollboxapi.daos;

import com.sigma.pollboxapi.domain.Code;
import com.sigma.pollboxapi.exceptions.NotFoundException;
import com.sigma.pollboxapi.mappers.CodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class CodeDao {
    private final JdbcTemplate template;

    public boolean insert(Code code) {
        String statement = "insert into codes(code, account_id, type) values (?,?,?)";
        try {
            return template.update(statement,
                    code.getCode(),
                    code.getAccountId(),
                    code.getType().toString().toLowerCase(Locale.ROOT)) > 0;
        } catch (DataAccessException e) {
            if (e.getLocalizedMessage().contains("Duplicate")) {
                return false;
            }
            throw e;
        }
    }

    public Code findUsingCode(String code) {
        String statement = "select * from codes where code=? and valid=?";
        try {
            return template.queryForObject(statement, new CodeMapper(), code, true);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Código não encontrado.");
        }
    }

    public boolean update(Code code) {
        String statement = "update codes set valid=? where account_id=?";
        return template.update(statement, code.isValid(), code.getAccountId()) > 0;
    }
}
