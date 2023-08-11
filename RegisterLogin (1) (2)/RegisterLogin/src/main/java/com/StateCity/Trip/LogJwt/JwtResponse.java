package com.StateCity.Trip.LogJwt;

import org.springframework.stereotype.Service;

@Service
public class JwtResponse {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	public JwtResponse() {
		super();
	}
	
}
