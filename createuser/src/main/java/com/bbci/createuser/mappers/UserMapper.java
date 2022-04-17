package com.bbci.createuser.mappers;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

import com.bbci.createuser.dtos.UserCompleteDto;
import com.bbci.createuser.dtos.UserDto;
import com.bbci.createuser.entities.User;

public class UserMapper {
	
	DozerBeanMapper mapper = new DozerBeanMapper();
	
	/*
	   * Transforma una lista de entity a una lista de dtos
	   * 
	   * @param List<User>
	   * 
	   * @return List<UserRequest>
	   */
	  public List<UserDto> fromListEntityToListDto(List<User> users) {
	    List<UserDto> dtoList = new ArrayList<UserDto>();
	    for (User user : users) {
	      dtoList.add(mapper.map(user, UserDto.class));
	    }
	    return dtoList;
	  }

	  /*
	   * Transforma de entity a dto
	   * 
	   * @param User
	   * 
	   * @return UserRequest
	   */
	  public UserDto fromEntityToDto(User user) {
	    if (user != null) {
	      return mapper.map(user, UserDto.class);
	    }
	    return null;
	  }
	  
	  /*
	   * Transforma de Dto a entity
	   * 
	   * @param UserRequest
	   * 
	   * @return User
	   */
	  public User fromDtoToEntity(UserDto user) {
	    return mapper.map(user, User.class);
	  }
	  
	  /*
	   * Transforma de entity a UserCompleteDto
	   * 
	   * @param User
	   * 
	   * @return UserCompleteDto
	   */
	  public UserCompleteDto fromEntityToCompleteDto(User user) {
	    if (user != null) {
	      return mapper.map(user, UserCompleteDto.class);
	    }
	    return null;
	  }
	  
	  /*
	   * Transforma de UserCompleteDto a User
	   * 
	   * @param UserCompleteDto
	   * 
	   * @return User
	   */
	  public User fromCompleteDtoToEntity(UserCompleteDto userDto) {
	    if (userDto != null) {
	      return mapper.map(userDto, User.class);
	    }
	    return null;
	  }

}
