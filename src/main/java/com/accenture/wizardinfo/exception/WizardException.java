package com.accenture.wizardinfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WizardException extends RuntimeException {

    public static final String INVALID_ID = "Wizard Id not found";

    public WizardException(String errorMessage) {
        super(errorMessage);
    }

}
