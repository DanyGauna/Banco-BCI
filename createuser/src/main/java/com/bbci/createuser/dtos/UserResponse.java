package com.bbci.createuser.dtos;

import com.bbci.createuser.errors.ErrorResponse;

import lombok.Data;

@Data
public class UserResponse {
	
	private Object data;
	private ErrorResponse error;

}
