package com.snipe.myTuitionCenter.admin.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends RuntimeException{
	public RoleNotFoundException(String message) {
        super(message);
    }
}
