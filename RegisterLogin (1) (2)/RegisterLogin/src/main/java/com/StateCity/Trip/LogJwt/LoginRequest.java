package com.StateCity.Trip.LogJwt;


import lombok.Data;

@Data
public class LoginRequest {
	private  String usernameOrMobileOrEmail;
	private String password;

}
