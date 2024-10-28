package edu.du.sb1024.spring;

import edu.du.sb1024.validation.EmailCheck;
import edu.du.sb1024.validation.PasswordMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@PasswordMatch
public class RegisterRequest {

	@Email(message = "유효한 이메일 주소를 입력해주세요.")
	@NotBlank(message = "이메일은 필수입니다.")
	@EmailCheck
	private String email;
	@NotBlank(message = "비밀번호는 필수입니다.")
	private String password;
	@NotBlank(message = "비밀번호 확인은 필수입니다.")
	private String confirmPassword;
	@NotBlank(message = "사용자 이름은 필수입니다.")
	private String name;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
	}
}
