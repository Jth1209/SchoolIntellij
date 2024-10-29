package edu.du.sb1024.spring;

import edu.du.sb1024.validation.EmailCheck;
import edu.du.sb1024.validation.PasswordMatch;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
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

    public void setEmail(String email) {
		this.email = email;
	}

    public void setPassword(String password) {
		this.password = password;
	}

    public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

    public void setName(String name) {
		this.name = name;
	}

	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
	}
}
