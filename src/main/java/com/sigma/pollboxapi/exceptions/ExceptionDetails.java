package com.sigma.pollboxapi.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class ExceptionDetails {
    private int status;
    private String title;
    private String message;
    private LocalDateTime datetime;
}
