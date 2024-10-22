package edu.du.sb1021_2.service;

import edu.du.sb1021_2.dao.Member;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Setter
public class ChangePasswordService {

	private MemberDao memberDao;

	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();

		member.changePassword(oldPwd, newPwd);

		memberDao.update(member);
	}

}
