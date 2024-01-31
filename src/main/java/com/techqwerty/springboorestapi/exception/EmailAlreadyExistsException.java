package com.techqwerty.springboorestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException {
    private String email;

    public EmailAlreadyExistsException(String messaage){
        super(messaage);
    }
}
