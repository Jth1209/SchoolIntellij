package edu.du.sb1024.entity;

import edu.du.sb1024.security.service.WrongIdPasswordException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false, unique = true)
	private String password;
	private String name;
	private String passwordCheck;
	private Date registerDateTime;
	private String role;

	public Member(String email, String password,
                  String name, Date regDateTime, String role) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = regDateTime;
		this.role = role;
	}

    public void changePassword(String oldPassword, String newPassword) {
		if (!password.equals(oldPassword))
			throw new WrongIdPasswordException();
		this.password = newPassword;
	}

}
