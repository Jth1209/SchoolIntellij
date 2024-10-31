package edu.du.proj_11m.userManagement;

import edu.du.proj_11m.userManagement.entity.RegisterRequest;
import edu.du.proj_11m.userManagement.exception.DuplicateMemberException;
import edu.du.proj_11m.userManagement.service.MemberRegisterService;
import edu.du.proj_11m.userManagement.validation.RegisterRequestValidator;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@Setter
@Controller
//@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private MemberRegisterService memberRegisterService;

    @GetMapping("/")
	public String root() {
		return "redirect:/register/step1";
	}

	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "/register/step1";
	}

	@PostMapping("/register/step2")
	public String handleStep2(
			@RequestParam(value = "agree", defaultValue = "false") Boolean agree,
			Model model) {
		if (!agree) {
			return "/register/step1";
		}
		model.addAttribute("registerRequest", new RegisterRequest());
		return "/register/step2";
	}

	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}

	@PostMapping("/register/step3")
	public String handleStep3(@ModelAttribute("registerRequest") RegisterRequest regReq, Errors errors) {
		new RegisterRequestValidator().validate(regReq, errors);
		if (errors.hasErrors())
			return "/register/step2";

		try {
			memberRegisterService.regist(regReq);
			return "/register/step3";
		} catch (DuplicateMemberException ex) {
			errors.rejectValue("email", "duplicate");
			return "/register/step2";
		}
	}

}
