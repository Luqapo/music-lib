package com.progspringinit.musiclib1.services;

import com.progspringinit.musiclib1.api.model.UserDTO;
import com.progspringinit.musiclib1.api.model.UsersListDTO;

public interface UserService {
	UsersListDTO getAllUsers();
	UserDTO getById(Long id);
	UsersListDTO getByFirstName(String name);
	UserDTO createNewUser(UserDTO userDTO);
	UserDTO updateUser(Long id, UserDTO userDTO);
	void deleteUserById(Long id);
}
