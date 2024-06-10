package com.snipe.myTuitionCenter.student.exception;

import static org.apache.http.HttpStatus.SC_OK;
import org.apache.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyTuitionCenterAppException  extends RuntimeException {

	private static final long serialVersionUID = -720664400716034134L;
	protected int httpErrorCode = SC_OK;
	protected int errorCode = Integer.MAX_VALUE;
	
	public MyTuitionCenterAppException() {
		super();
	}

	public MyTuitionCenterAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyTuitionCenterAppException(String message) {
		super(message);
	}

	public MyTuitionCenterAppException(Throwable cause) {
		super(cause);
	}
	
	public MyTuitionCenterAppException(int errCode, int httpErrCode, String message) {
		this(message);
		errorCode = errCode;
		httpErrorCode = httpErrCode;
	}
	public static final class BACKEND_SERVER_ERROR extends MyTuitionCenterAppException {
		private static final long serialVersionUID = 1L;

		public BACKEND_SERVER_ERROR() {
			super(1000, HttpStatus.SC_INTERNAL_SERVER_ERROR, String.format("Backend Server Error, Please Retry."));
		}
	}

	public static final class BACKEND_SERVER_READ_TIMEOUT extends MyTuitionCenterAppException {
		private static final long serialVersionUID = 1L;

		public BACKEND_SERVER_READ_TIMEOUT() {
			super(1001, HttpStatus.SC_GATEWAY_TIMEOUT,
					String.format("Backend Server read timeout Error, Please Retry."));
		}
	}

	public static final class USER_NOT_FOUND extends MyTuitionCenterAppException {
		private static final long serialVersionUID = 1L;

		public USER_NOT_FOUND() {
			super(1002, HttpStatus.SC_NOT_FOUND, String.format("User Not Found"));
		}
	}
	public static final class CONTACTDETAILS_NOT_FOUND extends MyTuitionCenterAppException {
		private static final long serialVersionUID = 1L;

		public CONTACTDETAILS_NOT_FOUND() {
			super(1003, HttpStatus.SC_NOT_FOUND, String.format("User Email/Phone no: Not Found"));
		}
	}
	public static final class SUBJECT_CATEGORY_NOT_FOUND extends MyTuitionCenterAppException {
		private static final long serialVersionUID = 1L;

		public SUBJECT_CATEGORY_NOT_FOUND() {
			super(1004, HttpStatus.SC_NOT_FOUND, String.format("Subject category not found in master "));
		}
	}
	public static final class TIME_SLOT_NOT_FOUND extends MyTuitionCenterAppException {
		private static final long serialVersionUID = 1L;

		public TIME_SLOT_NOT_FOUND() {
			super(1005, HttpStatus.SC_NOT_FOUND, String.format("Time slot not found in master "));
		}
	}
	public static final class STUDENT_NOT_FOUND extends MyTuitionCenterAppException {
		private static final long serialVersionUID = 1L;

		public STUDENT_NOT_FOUND() {
			super(1006, HttpStatus.SC_NOT_FOUND, String.format("Student not found for the given id"));
		}
	}
	public static final class STUDENT_SUBJECT_PREFERENCE_NOT_FOUND extends MyTuitionCenterAppException {
		private static final long serialVersionUID = 1L;

		public STUDENT_SUBJECT_PREFERENCE_NOT_FOUND() {
			super(1004, HttpStatus.SC_NOT_FOUND, String.format("Student subject preferences not found for given id "));
		}
	}
	
	public static final class STUDENT_PREFERENCE_ALREADY_EXISTS extends MyTuitionCenterAppException {
		private static final long serialVersionUID = 1L;

		public STUDENT_PREFERENCE_ALREADY_EXISTS() {
			super(1004, HttpStatus.SC_NOT_FOUND, String.format("Student subject preferences already exists for given id "));
		}
	}
	

}
