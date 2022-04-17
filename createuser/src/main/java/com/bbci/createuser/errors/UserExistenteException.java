package com.bbci.createuser.errors;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserExistenteException extends Exception {
	
	private static final long serialVersionUID = -123609995092455047L;
	
	private String message;
	private Integer code;

	public UserExistenteException(String message, Integer code) {
		super();
		this.message = message;
		this.code = code;
	}

}
