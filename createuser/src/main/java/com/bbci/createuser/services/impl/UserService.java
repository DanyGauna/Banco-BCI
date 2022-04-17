package com.bbci.createuser.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bbci.createuser.dtos.PhoneRequest;
import com.bbci.createuser.dtos.TokenRequest;
import com.bbci.createuser.dtos.UserCompleteDto;
import com.bbci.createuser.dtos.UserDto;
import com.bbci.createuser.dtos.UserRequest;
import com.bbci.createuser.entities.Phone;
import com.bbci.createuser.entities.User;
import com.bbci.createuser.errors.EmailException;
import com.bbci.createuser.errors.PasswordException;
import com.bbci.createuser.errors.TokenException;
import com.bbci.createuser.errors.UserExistenteException;
import com.bbci.createuser.mappers.UserMapper;
import com.bbci.createuser.repositories.IUserRepository;
import com.bbci.createuser.security.JwtProvider;
import com.bbci.createuser.services.IUserService;
import com.bbci.createuser.utils.ConstantesGenerales;
import com.bbci.createuser.utils.Utils;

@Service
public class UserService implements IUserService {

	@Autowired
	IUserRepository repository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtProvider jwtProvider;

	UserMapper mapper = new UserMapper();

	@Override
	public UserDto create(UserRequest userRequest) throws EmailException, PasswordException, UserExistenteException {
		UserDto response = new UserDto();
		//valida que el formato del email sea correcto de lo contrario lanza una exception controlada
		if (Utils.validaEmail(userRequest.getEmail())) {
			//valida que las caracteristicas de la contraseña sean correctas de lo contrario lanza una exception controlada
			String passwordValido = Utils.passwordValidator(userRequest.getPassword());
			if ("".equals(passwordValido)) {
				//verifica que el email no exista en la base de datos
				User usuario = repository.findByEmail(userRequest.getEmail());
				//si el email no existe lo crea
				if (usuario == null) {
					userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
					usuario = userMapper(userRequest);
					usuario.setToken(jwtProvider.createToken(usuario));
					response = mapper.fromEntityToDto(repository.save(usuario));
				} else {
					//si el email existe pero tiene el token expirado le genera uno nuevo y sino lanza una exception
					if (!jwtProvider.validate(usuario.getToken())) {
						usuario.setToken(jwtProvider.createToken(usuario));
						response = mapper.fromEntityToDto(repository.save(usuario));
					} else {
						throw new PasswordException(ConstantesGenerales.ERROR_USUARIO_EXISTENTE,
								ConstantesGenerales.ERROR_USUARIO_EXISTENTE_CODE);
					}
				}
			} else {
				throw new PasswordException(passwordValido, ConstantesGenerales.ERROR_PASSWORD_CODE);
			}
		} else {
			throw new EmailException(ConstantesGenerales.ERROR_FORMATO_EMAIL, ConstantesGenerales.ERROR_FORMATO_EMAIL_CODE);
		}
		return response;
	}

	private User userMapper(UserRequest userRequest) {
		return new User(null, UUID.randomUUID().toString().replace("-", ""), userRequest.getName(),
				userRequest.getEmail(), userRequest.getPassword(), new Date(), new Date(), "", true,
				phonesMapper(userRequest.getPhones()));
	}

	private List<Phone> phonesMapper(List<PhoneRequest> phones) {

		List<Phone> phonesList = new ArrayList<Phone>();
		phones.stream().forEach(phone -> {
			Phone phoneAux = new Phone();
			phoneAux.setCityCode(phone.getCityCode());
			phoneAux.setCountryCode(phone.getCountryCode());
			phoneAux.setNumber(phone.getNumber());
			phonesList.add(phoneAux);
		});
		return phonesList;
	}

	@Override
	public UserCompleteDto login(TokenRequest token) throws TokenException {
		//busca a un usuario en base al token enviado
		UserCompleteDto userDto = mapper.fromEntityToCompleteDto(repository.findByToken(token.getToken()));
		//si no existe lanza una excepcion contralada
		if (userDto == null) {
			throw new TokenException(ConstantesGenerales.BAD_TOKEN, ConstantesGenerales.BAD_TOKEN_CODE);
		} else {
			//si el usuario existe verifica que el token no haya expirado
			if (!jwtProvider.validate(token.getToken())) {
				throw new TokenException(ConstantesGenerales.TOKEN_EXPIRED, ConstantesGenerales.TOKEN_EXPIRED_CODE);
			} else {
				//si el usuario existe y el token no expiró se realiza un update de atributo lastLogin con la fecha actual
				User user = mapper.fromCompleteDtoToEntity(userDto);
				user.setLastLogin(new Date());
				repository.save(user);
				return userDto;
			}
		}
	}

}
