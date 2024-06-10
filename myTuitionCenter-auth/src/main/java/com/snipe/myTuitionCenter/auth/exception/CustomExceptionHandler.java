package com.snipe.myTuitionCenter.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.snipe.myTuitionCenter.auth.data.response.ErrorResponse;

@RestControllerAdvice
public class CustomExceptionHandler {
	
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
    	ErrorResponse errorResponse = new ErrorResponse("AUTH_001",null, ex.getMessage());
    	return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<ErrorResponse> handlePasswordIncorrectException(PasswordIncorrectException ex) {
    	ErrorResponse errorResponse = new ErrorResponse("AUTH_002",null, ex.getMessage());
    	return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
    
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponse> handleTokenExpiredException(TokenExpiredException ex) {
    	ErrorResponse errorResponse = new ErrorResponse("AUTH_003",null, ex.getMessage());
    	return new ResponseEntity<>(errorResponse, HttpStatus.REQUEST_TIMEOUT);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TokenUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleUnavailableException(TokenUnavailableException ex) {
    	ErrorResponse errorResponse = new ErrorResponse("AUTH_004",null, ex.getMessage());
    	return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> StudentNotFoundException(StudentNotFoundException ex) {
    	ErrorResponse errorResponse = new ErrorResponse("AUTH_005",null, ex.getMessage());
    	return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
