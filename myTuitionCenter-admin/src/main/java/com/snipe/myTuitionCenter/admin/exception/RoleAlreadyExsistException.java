package com.snipe.myTuitionCenter.admin.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleAlreadyExsistException extends RuntimeException{
	public RoleAlreadyExsistException(String message) {
        super(message);
    }
}
