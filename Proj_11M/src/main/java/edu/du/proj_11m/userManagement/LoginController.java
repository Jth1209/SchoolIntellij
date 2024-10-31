package edu.du.proj_11m.userManagement;

import edu.du.proj_11m.userManagement.command.LoginCommand;
import edu.du.proj_11m.userManagement.entity.AuthInfo;
import edu.du.proj_11m.userManagement.exception.WrongIdPasswordException;
import edu.du.proj_11m.userManagement.service.AuthService;
import edu.du.proj_11m.userManagement.validation.LoginCommandValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AuthService authService;
	
	@GetMapping
	public String form(LoginCommand loginCommand) {
		return "/login/loginForm";
	}
	
	@PostMapping
	public String submit(@ModelAttribute("loginCommand") LoginCommand loginCommand, Errors errors, HttpSession session) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) {
			return "/login/loginForm";
		}
		try {
			AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			session.setAttribute("authInfo", authInfo);
			return "/login/loginSuccess";
		}catch (WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "/login/loginForm";
		}
	}
}
