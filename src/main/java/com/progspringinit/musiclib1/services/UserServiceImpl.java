package com.progspringinit.musiclib1.services;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.progspringinit.musiclib1.api.mapper.UserMapper;
import com.progspringinit.musiclib1.api.model.AuthenticationResponse;
import com.progspringinit.musiclib1.api.model.LoginRequestModel;
import com.progspringinit.musiclib1.api.model.UserDTO;
import com.progspringinit.musiclib1.api.model.UsersListDTO;
import com.progspringinit.musiclib1.domain.User;
import com.progspringinit.musiclib1.repositories.UserRepository;
import com.progspringinit.musiclib1.utils.JwtUtil;


@Service
public class UserServiceImpl implements UserService {
	UserRepository userRepository;
	UserMapper userMapper;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	MyUserDetailsService myUserrDetailService;
	@Autowired
	JwtUtil jwtUtil;

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

	@Override
	public AuthenticationResponse authnticateUser(LoginRequestModel loginRequest) {
		try {       
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginRequest.getEmail(),
							loginRequest.getPassword()
							)
		            );
		            
		        } catch (BadCredentialsException e) {
		            throw new RuntimeException(e);
		        }
		
		UserDTO userDto = myUserrDetailService.getUserByEmail(loginRequest.getEmail());
		String token = jwtUtil.generateToken(userDto.getEmail());
		
		return new AuthenticationResponse(token);
	}
}
