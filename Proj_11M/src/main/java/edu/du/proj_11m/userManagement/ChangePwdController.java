package edu.du.proj_11m.userManagement;

import edu.du.proj_11m.userManagement.command.ChangePwdCommand;
import edu.du.proj_11m.userManagement.entity.AuthInfo;
import edu.du.proj_11m.userManagement.exception.WrongIdPasswordException;
import edu.du.proj_11m.userManagement.service.ChangePasswordService;
import edu.du.proj_11m.userManagement.validation.ChangePwdCommandValidator;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


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
