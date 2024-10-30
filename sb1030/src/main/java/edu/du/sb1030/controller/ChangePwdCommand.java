package edu.du.sb1030.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePwdCommand {

	private String currentPassword;
	private String newPassword;

}
