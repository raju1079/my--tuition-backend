package com.snipe.myTuitionCenter.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TokenUnavailableException  extends RuntimeException{
    public TokenUnavailableException(String message) {
        super(message);
    }

}
