package com.bbci.createuser.errors;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PasswordException extends Exception {
	
	private static final long serialVersionUID = -4321867461266988442L;

	private String message;
	private Integer code;

	public PasswordException(String message, Integer code) {
		super();
		this.message = message;
		this.code = code;
	}
}
