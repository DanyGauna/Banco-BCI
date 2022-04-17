package com.bbci.createuser.dtos;

import java.util.List;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class UserRequest {
	@NotNull
	private String name;
	@NotNull
	private String email;
	private String password;
	private List<PhoneRequest> phones;
}
