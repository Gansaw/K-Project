package com.airport.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member {	
	@Id
	private String username;	
	private String password;
	private String nickname;
	private String role;	
	private String email;
	private boolean enabled;	
	
    public String getRole() {
        return role;
    }

}
