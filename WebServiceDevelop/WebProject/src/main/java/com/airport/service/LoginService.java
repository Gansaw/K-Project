package com.airport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.airport.domain.Member;
import com.airport.persistence.MemberRepo;

@Service
public class LoginService {
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Member getMember(Member member) {
		Optional<Member> findMember = memberRepo.findById(member.getUsername());
		if(findMember.isPresent())
			return findMember.get();
		else
			return null;
	}
	
	public boolean existsByUsername(String username) {
		return memberRepo.existsByUsername(username);
	}
	
	public boolean existsByNickname(String nickname) {
		return memberRepo.existsByNickname(nickname);
	}

	public void save(Member member) {
	    String encodedPassword = encoder.encode(member.getPassword());
	    member.setPassword(encodedPassword);
		memberRepo.save(member);
	}
}
