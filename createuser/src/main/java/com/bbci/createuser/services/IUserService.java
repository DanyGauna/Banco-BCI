package com.bbci.createuser.services;

import com.bbci.createuser.dtos.TokenRequest;
import com.bbci.createuser.dtos.UserDto;
import com.bbci.createuser.dtos.UserRequest;
import com.bbci.createuser.errors.EmailException;
import com.bbci.createuser.errors.PasswordException;
import com.bbci.createuser.errors.TokenException;
import com.bbci.createuser.errors.UserExistenteException;

public interface IUserService {
	
	UserDto create(UserRequest userRequest) throws EmailException, PasswordException, UserExistenteException;
	
	UserDto login(TokenRequest token) throws TokenException;

}
