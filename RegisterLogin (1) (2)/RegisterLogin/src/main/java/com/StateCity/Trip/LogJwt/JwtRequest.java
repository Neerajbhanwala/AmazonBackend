package com.StateCity.Trip.LogJwt;

import java.util.Set;

import lombok.Data;
import org.springframework.stereotype.Service;

import com.StateCity.Trip.LogUser.Role;

@Data
public class JwtRequest {
	private String username;
	private String mobile;
	private String name;
	private String password;
	private String roles;
	
	

}
