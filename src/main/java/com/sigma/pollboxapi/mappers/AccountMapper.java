package com.sigma.pollboxapi.mappers;

import com.sigma.pollboxapi.domain.Account;
import com.sigma.pollboxapi.utils.LocalDateTimeFormatter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AccountMapper implements RowMapper<Account> {
    public static class Safe extends AccountMapper {
        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Account.builder()
                    .email(rs.getString("email"))
                    .username(rs.getString("username"))
                    .createdAt(LocalDateTimeFormatter.brazilianFormat(rs.getString("created_at")))
                    .build();
        }
    }

    public static class Full extends AccountMapper {
        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Account.builder()
                    .id(rs.getLong("id"))
                    .email(rs.getString("email"))
                    .username(rs.getString("username"))
                    .password(rs.getString("password"))
                    .authenticated(rs.getBoolean("authenticated"))
                    .createdAt(LocalDateTimeFormatter.brazilianFormat(rs.getString("created_at")))
                    .build();
        }
    }
}
