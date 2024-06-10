package com.snipe.myTuitionCenter.student.common;

import static com.snipe.myTuitionCenter.student.common.Status.SUCCESS;
import static com.snipe.myTuitionCenter.student.common.Status.FAILURE;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class GenericResponse <T>{
		@JsonIgnore
		private Integer httpStatusCode = 200;
		private String exCode;
		private Status status = SUCCESS;
		private T data;
		private String message;
		@JsonIgnore
		private Throwable exception;
		
		public GenericResponse(Throwable exception) {
			if (exception == null) {
				return;
			}
			exCode = exception.getClass().getSimpleName();
			status= FAILURE;
			this.setException(exception);
		}

		
}
