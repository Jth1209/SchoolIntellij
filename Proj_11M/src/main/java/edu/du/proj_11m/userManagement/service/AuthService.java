package edu.du.proj_11m.userManagement.service;

import edu.du.proj_11m.userManagement.entity.AuthInfo;
import edu.du.proj_11m.userManagement.entity.Member;
import edu.du.proj_11m.userManagement.entity.MemberDao;
import edu.du.proj_11m.userManagement.exception.WrongIdPasswordException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Setter
@Service
public class AuthService {

	@Autowired
	private MemberDao memberDao;

	public AuthService(MemberDao memberDao) {
		super();
		this.memberDao = memberDao;
	}
	
	public AuthService() {
		super();
	}

    public AuthInfo authenticate(String email, String password) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new WrongIdPasswordException();
		}
		if(!member.matchPasswrod(password)) {
			throw new WrongIdPasswordException();
		}
		return new AuthInfo(member.getId(),member.getEmail(),member.getName());
	}
}
