package com.bbci.createuser.dtos;

import java.util.List;

import com.bbci.createuser.entities.Phone;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserCompleteDto extends UserDto{
	
	private String name;
	private String email;
	private String password;
	private List<Phone> phones;

}
