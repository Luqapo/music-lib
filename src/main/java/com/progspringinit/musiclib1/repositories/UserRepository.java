package com.progspringinit.musiclib1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.progspringinit.musiclib1.domain.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	public List<User> getByFirstName(String name);
	public User findByEmail(String email);
}
