package com.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.airport.domain.Member;
import com.airport.service.LoginService;

@RestController
@SessionAttributes("회원")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@GetMapping("/login")
	public void login() {
		
	}	
	
	@PostMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = loginService.getMember(member);
		if(findMember != null && findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("회원", findMember);		
		}
		return "main";
	}
	
	@GetMapping("/signup")
	public void signup() {		
		
	}
	
    @PostMapping("/signup")
    public ResponseEntity<String> signupProc(@RequestBody Member member) {
    	
    	if (loginService.existsByUsername(member.getUsername()))
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 아이디입니다.");
    	
    	if (loginService.existsByNickname(member.getNickname()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 닉네임입니다.");
    	
    	if (member.getRole() == null)
            member.setRole("회원");

        member.setEnabled(true);
        loginService.save(member);       
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
    }
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "login";
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		
	}
	
	@GetMapping("/errors/accessDeny")
	public void accessDeny() {
		
	}
	
}
