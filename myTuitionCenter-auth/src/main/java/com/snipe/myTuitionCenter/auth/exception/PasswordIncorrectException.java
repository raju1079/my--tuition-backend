package com.snipe.myTuitionCenter.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(String message) {
        super(message);
    }
}
