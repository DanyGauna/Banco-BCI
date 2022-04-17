package com.bbci.createuser.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbci.createuser.dtos.TokenRequest;
import com.bbci.createuser.dtos.UserRequest;
import com.bbci.createuser.dtos.UserResponse;
import com.bbci.createuser.errors.EmailException;
import com.bbci.createuser.errors.ErrorResponse;
import com.bbci.createuser.errors.PasswordException;
import com.bbci.createuser.errors.TokenException;
import com.bbci.createuser.errors.UserExistenteException;
import com.bbci.createuser.services.IUserService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("/user")
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	IUserService service;
	
	@PostMapping("/sign-up")
	@JsonFormat
	public UserResponse signUp(@RequestBody UserRequest userRequest) {
		logger.info("::::Inicio método signUp::::");
		logger.info("::::userRequest: "+userRequest.getEmail()+"::::");
		UserResponse response = new UserResponse();
		try {
			response.setData(service.create(userRequest));
		} catch (EmailException e) {
			response.setError(new ErrorResponse( e.getCode(),e.getMessage()));
			logger.error(e.getMessage());
		} catch (PasswordException e) {
			response.setError(new ErrorResponse( e.getCode(),e.getMessage()));
			logger.error(e.getMessage());
		} catch (UserExistenteException e) {
			response.setError(new ErrorResponse( e.getCode(),e.getMessage()));
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(new ErrorResponse(500, HttpStatus.INTERNAL_SERVER_ERROR.toString()));
		}
		logger.info("::::Finaliza método signUp::::");
		return response;
	}
	
	@PostMapping("/login")
	@JsonFormat
	public UserResponse login(@RequestBody TokenRequest token) {
		logger.info("::::Inicio método login::::");
		logger.info("::::token: "+token+"::::");
		UserResponse response = new UserResponse();
		try {
			response.setData(service.login(token));
		}  catch (TokenException e) {
			response.setError(new ErrorResponse( e.getCode(),e.getMessage()));
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(new ErrorResponse(500, HttpStatus.INTERNAL_SERVER_ERROR.toString()));
		}
		logger.info("::::Finaliza método login::::");
		return response;
	}

}
