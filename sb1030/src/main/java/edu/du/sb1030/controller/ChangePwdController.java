package edu.du.sb1030.controller;

import javax.servlet.http.HttpSession;

import edu.du.sb1030.spring.AuthInfo;
import edu.du.sb1030.spring.ChangePasswordService;
import edu.du.sb1030.spring.WrongIdPasswordException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Setter
@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {

	@Autowired
	private ChangePasswordService changePasswordService;

    @GetMapping
	public String form(@ModelAttribute("command") ChangePwdCommand pwdCommand) {
		return "/edit/changePwdForm";
	}
	
	@PostMapping
	public String form(@ModelAttribute("command") ChangePwdCommand pwdCommand , Errors errors,HttpSession session) {
		new ChangePwdCommandValidator().validate(pwdCommand, errors);
		if(errors.hasErrors()) {
			return "/edit/changePwdForm";
		}
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		try {
			changePasswordService.changePassword(authInfo.getEmail(), pwdCommand.getCurrentPassword(), pwdCommand.getNewPassword());;
			return "/edit/changePwd";
		}catch (WrongIdPasswordException e) {
			errors.rejectValue("currentPassword","notMatching");
			return "/edit/changePwdForm";
		}
	}
}
