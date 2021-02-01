package com.progspringinit.musiclib1.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.progspringinit.musiclib1.api.mapper.UserMapper;
import com.progspringinit.musiclib1.api.model.UserDTO;
import com.progspringinit.musiclib1.repositories.UserRepository;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserMapper userMapper;
	
	public UserDTO getUserByEmail(String email) {
        UserDTO userEntity = userMapper.userToDTO(userRepository.findByEmail(email));
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return userEntity;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO userEntity = userMapper.userToDTO(userRepository.findByEmail(email));
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(), userEntity.getPassword(),
                true, // Email verification status
                true, true,
                true, new ArrayList<>());
    }

}
