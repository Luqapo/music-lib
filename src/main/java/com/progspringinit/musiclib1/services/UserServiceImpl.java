package com.progspringinit.musiclib1.services;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.progspringinit.musiclib1.api.mapper.UserMapper;
import com.progspringinit.musiclib1.api.model.UserDTO;
import com.progspringinit.musiclib1.api.model.UsersListDTO;
import com.progspringinit.musiclib1.domain.User;
import com.progspringinit.musiclib1.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	UserRepository userRepository;
	UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UsersListDTO getAllUsers() {
		
		return new UsersListDTO(userRepository.findAll()
				.stream()
				.map(userMapper::userToDTO)
				.collect(Collectors.toList()));
	}

	@Override
	public UserDTO getById(Long id) {
		
		return userMapper.userToDTO(userRepository.findById(id).get());
	}

	@Override
	public UsersListDTO getByFirstName(String name) {
		
		return new UsersListDTO(userRepository.getByFirstName(name)
				.stream()
				.map(userMapper::userToDTO)
				.collect(Collectors.toList()));
	}

	@Override
	public UserDTO createNewUser(UserDTO userDTO) {
		
		User user = userMapper.userDTOToUser(userDTO);
		User savedUser = userRepository.save(user);
		return userMapper.userToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(Long id, UserDTO userDTO) {
		
		User user = userMapper.userDTOToUser(userDTO);
		user.setId(id);
		User savedUser = userRepository.save(user);
		return userMapper.userToDTO(savedUser);
	}

	@Override
	public void deleteUserById(Long id) {
		
		userRepository.deleteById(id);
	}
}
