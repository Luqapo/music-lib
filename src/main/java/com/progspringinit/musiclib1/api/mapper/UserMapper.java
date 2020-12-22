package com.progspringinit.musiclib1.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.progspringinit.musiclib1.api.model.UserDTO;
import com.progspringinit.musiclib1.domain.User;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserDTO userToDTO(User user);
	@Mapping(target = "playlists", ignore = true)
	User userDTOToUser(UserDTO userDTO);
}
