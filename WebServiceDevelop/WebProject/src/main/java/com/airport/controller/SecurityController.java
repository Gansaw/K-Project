package com.airport.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	@GetMapping("/")
	public void forAll() {
		System.out.println("비로그인자 요청입니다.");

	}	
	
	@GetMapping("/user")
	public void forUser() {
		System.out.println("회원 요청입니다.");

	}	
	@GetMapping("/admin")
	public void forAdmin() {
		System.out.println("관리자 요청입니다.");
	}

}
