package edu.du.db1010.spring;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//@ManualBean
@Repository
public class MemberDao {

	private static long nextId = 0;

	private Map<String, Member> map = new HashMap<>();

	public Member selectByEmail(String email) {
		return map.get(email);
	}

	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
	}

	public void update(Member member) {
		map.put(member.getEmail(), member);
	}

	public Collection<Member> selectAll() {
		return map.values();
	}
}
