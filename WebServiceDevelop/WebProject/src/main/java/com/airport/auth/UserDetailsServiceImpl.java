package com.airport.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.airport.domain.Member;
import com.airport.persistence.MemberRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	public MemberRepo memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> option = memberRepo.findById(username);
		if(!option.isPresent()){
			throw new UsernameNotFoundException("사용자가 존재하지 않습니다.");
		}
		Member member = option.get();
				
		System.out.println(member);
		
		return new UserDetailsImpl(member);
	}

}
