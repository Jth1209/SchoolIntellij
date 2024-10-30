package edu.du.sb1030.spring;

import lombok.Getter;

@Getter
public class AuthInfo {
	
	private Long id;
	private String email;
	private String password;
	
	public AuthInfo(Long id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}


}
