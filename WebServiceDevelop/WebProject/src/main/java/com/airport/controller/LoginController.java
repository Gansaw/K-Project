package com.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.airport.domain.Member;
import com.airport.jwt.JwtUtil;
import com.airport.service.LoginService;

@RestController
public class LoginController {
    
    @Autowired
    private LoginService loginService;

    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @GetMapping("/login")
    public void login() {
        
    }   
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Member member) {
        String token = loginService.login(member);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
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
        // 비밀번호 암호화
        String encodedPassword = encoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        loginService.save(member);       
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
    }
    
    @GetMapping("/logout")
    public String logout(SessionStatus status) {
        status.setComplete();
        return "login";
    }

	public JwtUtil getJwtUtil() {
		return jwtUtil;
	}

	public void setJwtUtil(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
    
}
