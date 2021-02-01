package com.progspringinit.musiclib1.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.progspringinit.musiclib1.api.model.UserDTO;

public interface MyUserDetailsService extends UserDetailsService {
	public UserDTO getUserByEmail(String email);
}
