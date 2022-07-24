package com.sigma.pollboxapi.exceptions;

import lombok.Getter;

@Getter
public class DuplicatedEntryException extends RuntimeException {
    public DuplicatedEntryException(String message) {
        super(message);
    }
}
