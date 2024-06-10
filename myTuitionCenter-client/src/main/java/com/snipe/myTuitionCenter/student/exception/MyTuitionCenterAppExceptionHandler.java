package com.snipe.myTuitionCenter.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.snipe.myTuitionCenter.student.common.GenericResponse;
import com.snipe.myTuitionCenter.student.exception.MyTuitionCenterAppException.BACKEND_SERVER_ERROR;
import com.snipe.myTuitionCenter.student.exception.MyTuitionCenterAppException.BACKEND_SERVER_READ_TIMEOUT;
import com.snipe.myTuitionCenter.student.exception.MyTuitionCenterAppException.CONTACTDETAILS_NOT_FOUND;
import com.snipe.myTuitionCenter.student.exception.MyTuitionCenterAppException.STUDENT_NOT_FOUND;
import com.snipe.myTuitionCenter.student.exception.MyTuitionCenterAppException.SUBJECT_CATEGORY_NOT_FOUND;
import com.snipe.myTuitionCenter.student.exception.MyTuitionCenterAppException.TIME_SLOT_NOT_FOUND;
import com.snipe.myTuitionCenter.student.exception.MyTuitionCenterAppException.USER_NOT_FOUND;

import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class MyTuitionCenterAppExceptionHandler extends ResponseEntityExceptionHandler {
	
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(USER_NOT_FOUND.class)
    public ResponseEntity<GenericResponse<Object>> handleUserNotFoundException(USER_NOT_FOUND ex) {
    	return HttpResponseUtils.prepareErrorResponse(ex,ex.getHttpErrorCode(),ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CONTACTDETAILS_NOT_FOUND.class)
    public ResponseEntity<GenericResponse<Object>> handleContactDetailsNotFoundException(CONTACTDETAILS_NOT_FOUND ex) {
        return HttpResponseUtils.prepareErrorResponse(ex,ex.getHttpErrorCode(),ex.getMessage());
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SUBJECT_CATEGORY_NOT_FOUND.class)
    public ResponseEntity<GenericResponse<Object>> handleSubjectCategorysNotFoundException(SUBJECT_CATEGORY_NOT_FOUND ex) {
        return HttpResponseUtils.prepareErrorResponse(ex,ex.getHttpErrorCode(),ex.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TIME_SLOT_NOT_FOUND.class)
    public ResponseEntity<GenericResponse<Object>> handleTimeSlotNotFoundException(TIME_SLOT_NOT_FOUND ex) {
        return HttpResponseUtils.prepareErrorResponse(ex,ex.getHttpErrorCode(),ex.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(STUDENT_NOT_FOUND.class)
    public ResponseEntity<GenericResponse<Object>> handleStudentNotFoundException(STUDENT_NOT_FOUND ex) {
        return HttpResponseUtils.prepareErrorResponse(ex,ex.getHttpErrorCode(),ex.getMessage());
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BACKEND_SERVER_ERROR.class)
	private ResponseEntity<GenericResponse<Object>> handleBackendErrorException(BACKEND_SERVER_ERROR ex) {
		return HttpResponseUtils.prepareErrorResponse(ex,ex.getHttpErrorCode(),ex.getMessage());
	}
    
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    @ExceptionHandler(BACKEND_SERVER_READ_TIMEOUT.class)
	private ResponseEntity<GenericResponse<Object>> handleBackendReadTimeOutException(BACKEND_SERVER_READ_TIMEOUT ex) {
		return HttpResponseUtils.prepareErrorResponse(ex,ex.getHttpErrorCode(),ex.getMessage());
	}
    @ExceptionHandler(Throwable.class)
	private ResponseEntity<GenericResponse<Object>> handleException(Throwable ex, WebRequest request,
			HttpServletResponse response) throws Exception {
		return HttpResponseUtils.prepareErrorResponse(ex,HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage());
	}
   
    
}
