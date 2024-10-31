package edu.du.proj_11m.userManagement.entity;

import lombok.Getter;

@Getter
public class AuthInfo {
	
	private Long id;
	private String email;
	private String name;
	
	public AuthInfo(Long id, String email, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
	}


}
