package com.progspringinit.musiclib1.api.model;



import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String password;
}
