package com.sigma.pollboxapi.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Code {
    private long id;
    private String code;
    private long accountId;
    private boolean valid;
    private CodeType type;
    private LocalDateTime createdAt;
}
