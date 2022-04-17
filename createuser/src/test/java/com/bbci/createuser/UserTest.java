package com.bbci.createuser;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbci.createuser.controllers.UserController;
import com.bbci.createuser.dtos.PhoneRequest;
import com.bbci.createuser.dtos.UserDto;
import com.bbci.createuser.dtos.UserRequest;
import com.bbci.createuser.dtos.UserResponse;
import com.bbci.createuser.entities.User;
import com.bbci.createuser.services.IUserService;
import com.bbci.createuser.utils.ConstantesGenerales;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = UserTest.class)
public class UserTest {

	@Mock
	private UserDto userDto;
	@Mock
	private User user;
	@Mock
	private UserRequest userRequest;
	@Mock
	List<PhoneRequest> phones;
	@Mock
	private UserResponse userResponse;
	@InjectMocks
	private IUserService service;
	@InjectMocks
	private UserController controller;
	
	@Test
	public void testCreateUserExitoso() {

		userRequest = Mockito.spy(new UserRequest());
		userRequest.setEmail("julio@testssw.cl");
		userRequest.setName("Julio Gonzales");
		userRequest.setPassword("a2asfGfdfdf4");
		phones = Mockito.spy(new ArrayList<PhoneRequest>());
		phones.add(fillPhones());
		userRequest.setPhones(phones);

		Assert.assertNotNull(controller.signUp(userRequest).getData());
	}
	
	@Test
	public void testCreateUserWithError() {

		userRequest = Mockito.spy(new UserRequest());
		userRequest.setEmail("julio@testssw.cl");
		userRequest.setName("Julio Gonzales");
		userRequest.setPassword("a2asfGfdfdf4");
		phones = Mockito.spy(new ArrayList<PhoneRequest>());
		phones.add(fillPhones());
		userRequest.setPhones(phones);

		Assert.assertNotNull(controller.signUp(userRequest).getError());
	}

	@Test
	public void testCreateUserEmailIncorrecto() {

		userRequest = Mockito.spy(new UserRequest());
		userRequest.setEmail("juliotestssw.cl");
		userRequest.setName("Julio Gonzales");
		userRequest.setPassword("a2asfGfdfdf4");
		phones = Mockito.spy(new ArrayList<PhoneRequest>());
		phones.add(fillPhones());
		userRequest.setPhones(phones);

		Assert.assertEquals(ConstantesGenerales.ERROR_FORMATO_EMAIL, controller.signUp(userRequest).getError().getDetail());
	}

	@Test
	public void testCreateUserPassordInvalido() {

		userRequest = Mockito.spy(new UserRequest());
		userRequest.setEmail("julio@testssw.cl");
		userRequest.setName("Julio Gonzales");
		userRequest.setPassword("aasffdfdf4");
		phones = Mockito.spy(new ArrayList<PhoneRequest>());
		phones.add(fillPhones());
		userRequest.setPhones(phones);

		Assert.assertEquals(ConstantesGenerales.ERROR_PASSWORD, controller.signUp(userRequest).getError().getDetail());
	}
	
	private PhoneRequest fillPhones() {
		PhoneRequest phone = new PhoneRequest();
		phone.setCityCode(1);
		phone.setCountryCode("02");
		phone.setNumber(123456L);

		return phone;
	}

}
