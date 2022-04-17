package com.bbci.createuser.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class UserDto {

	private Long id;
	private String uuid;
	private Date created;
	private Date lastLogin;
	@JsonInclude(value = Include.NON_EMPTY)
	private String token;
	private Boolean isActivo;

}
