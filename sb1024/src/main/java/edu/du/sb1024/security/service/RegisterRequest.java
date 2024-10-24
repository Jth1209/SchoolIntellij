package edu.du.sb1024.security.service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {

	private String email;
	private String password;
	private String confirmPassword;
	private String name;

    public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
	}
}
