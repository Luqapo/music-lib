package com.progspringinit.musiclib1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.progspringinit.musiclib1.api.model.AuthenticationResponse;
import com.progspringinit.musiclib1.api.model.LoginRequestModel;
import com.progspringinit.musiclib1.api.model.UserDTO;
import com.progspringinit.musiclib1.api.model.UsersListDTO;
import com.progspringinit.musiclib1.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public UsersListDTO getAllUsers() {
		
		return userService.getAllUsers();
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDTO getUserById(@PathVariable Long id) {
		
		return userService.getById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO createNewUser(@RequestBody UserDTO userDTO) {
		return userService.createNewUser(userDTO);
	}
	
	@PostMapping("/authenticate")
	@ResponseStatus(HttpStatus.OK)
	public AuthenticationResponse createAuthenticationToken(@RequestBody LoginRequestModel loginRequest) {
		return userService.authnticateUser(loginRequest);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		return userService.updateUser(id, userDTO);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUSer(@PathVariable Long id) {
		userService.deleteUserById(id);
	}
}
