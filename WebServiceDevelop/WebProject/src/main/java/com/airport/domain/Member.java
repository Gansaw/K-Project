package com.airport.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="member")
public class Member {	
	@Id
	private String username;	
	private String password;
	private String nickname;
	private String role;
	private String email;
	private boolean enabled;
	
	public  Collection<? extends GrantedAuthority> getAuthority(){
		return AuthorityUtils.createAuthorityList(role);
	}
	
}
