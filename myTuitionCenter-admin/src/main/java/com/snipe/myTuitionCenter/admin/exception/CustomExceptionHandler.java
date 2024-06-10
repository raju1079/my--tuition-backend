package com.snipe.myTuitionCenter.admin.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(RoleNotFoundException.class)
    public Map<String, String> handleRoleNotFoundException(RoleNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        errorMap.put("errorCode", "ER001");
        return errorMap ;
    }
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(RoleAlreadyExsistException.class)
    public Map<String, String> handleRoleExsistException(RoleAlreadyExsistException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        errorMap.put("errorCode", "ER002");
        return errorMap ;
    } 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserException.class)
	public Map<String, String> handleUserException(UserException ex){
		Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        errorMap.put("errorCode", "USER001");
		return errorMap;
		
	}
}
