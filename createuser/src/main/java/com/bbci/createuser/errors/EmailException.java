package com.bbci.createuser.errors;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class EmailException extends Exception {
	
	private static final long serialVersionUID = -6221232067352000743L;

	private String message;
	private Integer code;

	public EmailException(String message, Integer code) {
		super();
		this.message = message;
		this.code = code;
	}

}
