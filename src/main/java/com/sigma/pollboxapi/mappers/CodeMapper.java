package com.sigma.pollboxapi.mappers;


import com.sigma.pollboxapi.domain.Code;
import com.sigma.pollboxapi.domain.CodeType;
import com.sigma.pollboxapi.utils.LocalDateTimeFormatter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class CodeMapper implements RowMapper<Code> {
    @Override
    public Code mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Code.builder()
                .id(rs.getLong("id"))
                .code(rs.getString("code"))
                .accountId(rs.getLong("account_id"))
                .type(CodeType.valueOf(rs.getString("type").toUpperCase(Locale.ROOT)))
                .valid(rs.getBoolean("valid"))
                .createdAt(LocalDateTimeFormatter.fromDatabase(rs.getString("created_at")))
                .build();
    }
}
