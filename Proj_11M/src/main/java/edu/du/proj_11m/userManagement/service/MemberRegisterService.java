package edu.du.proj_11m.userManagement.service;

import edu.du.proj_11m.userManagement.entity.Member;
import edu.du.proj_11m.userManagement.entity.MemberDao;
import edu.du.proj_11m.userManagement.entity.RegisterRequest;
import edu.du.proj_11m.userManagement.exception.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberRegisterService {

	@Autowired
	private MemberDao memberDao;

	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(), 
				LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
