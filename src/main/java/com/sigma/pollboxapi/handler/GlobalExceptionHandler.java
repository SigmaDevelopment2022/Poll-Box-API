package com.sigma.pollboxapi.handler;

import com.sigma.pollboxapi.exceptions.DuplicatedEntryException;
import com.sigma.pollboxapi.exceptions.ExceptionDetails;
import com.sigma.pollboxapi.exceptions.NotFoundException;
import com.sigma.pollboxapi.exceptions.ValidationExceptionDetails;
import com.sigma.pollboxapi.utils.LocalDateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .datetime(LocalDateTimeFormatter.format(LocalDateTime.now()))
                .title("Recurso não encontrado")
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .datetime(LocalDateTimeFormatter.format(LocalDateTime.now()))
                .title("Recurso não encontrado")
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> errors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        List<String> fields = errors.stream().map(FieldError::getField).collect(Collectors.toList());
        List<String> fieldsMessage = errors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return new ResponseEntity<>(ValidationExceptionDetails.builder()
                .datetime(LocalDateTimeFormatter.format(LocalDateTime.now()))
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Exceção de validação")
                .message("Alguns campos podem ter problemas, veja em fields e fieldsMessages.")
                .fields(fields)
                .fieldsMessages(fieldsMessage)
                .build(), HttpStatus.BAD_REQUEST);
    }
}
