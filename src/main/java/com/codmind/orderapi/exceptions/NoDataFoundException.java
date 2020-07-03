package com.codmind.orderapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NO_CONTENT)
public class NoDataFoundException extends RuntimeException{

	public NoDataFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoDataFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoDataFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoDataFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoDataFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
