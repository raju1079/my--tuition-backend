package com.snipe.myTuitionCenter.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
public class TokenExpiredException  extends RuntimeException{
    public TokenExpiredException(String message) {
        super(message);
    }

}
