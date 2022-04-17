package com.bbci.createuser.errors;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TokenException extends Exception {

	private static final long serialVersionUID = 9183182830113612568L;
	
	private String message;
	private Integer code;

	public TokenException(String message, Integer code) {
		super();
		this.message = message;
		this.code = code;
	}

}
