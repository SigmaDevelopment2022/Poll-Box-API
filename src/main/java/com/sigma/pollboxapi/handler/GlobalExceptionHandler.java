package com.sigma.pollboxapi.handler;

import com.sigma.pollboxapi.exceptions.DuplicatedEntryException;
import com.sigma.pollboxapi.exceptions.ExceptionDetails;
import com.sigma.pollboxapi.utils.LocalDateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicatedEntryException.class)
    public ResponseEntity<ExceptionDetails> handleDuplicatedEntryException(DuplicatedEntryException e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .datetime(LocalDateTimeFormatter.format(LocalDateTime.now()))
                .title("Recurso duplicado")
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .build(), HttpStatus.CONFLICT);
    }
}
