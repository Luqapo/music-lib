package com.progspringinit.musiclib1.api.model;

import lombok.Data;

@Data
public class LoginRequestModel {
	private String email;
	private String password;
}
