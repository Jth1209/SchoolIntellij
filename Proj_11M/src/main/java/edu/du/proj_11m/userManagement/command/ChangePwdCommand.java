package edu.du.proj_11m.userManagement.command;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePwdCommand {

	private String currentPassword;
	private String newPassword;

}
