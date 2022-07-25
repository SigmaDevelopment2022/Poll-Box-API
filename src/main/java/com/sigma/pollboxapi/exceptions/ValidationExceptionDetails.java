package com.sigma.pollboxapi.exceptions;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public class ValidationExceptionDetails extends ExceptionDetails {
    private List<String> fields;
    private List<String> fieldsMessages;
}
