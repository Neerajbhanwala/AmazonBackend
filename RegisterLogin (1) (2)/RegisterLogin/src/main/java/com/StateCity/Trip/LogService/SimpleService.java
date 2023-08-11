package com.StateCity.Trip.LogService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.StateCity.Trip.LogRepository.UserRepository;
import com.StateCity.Trip.LogUser.Users;

@Service
public class SimpleService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Users adding(Users user) {
		
	   user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);	
	}
	
   public List<Users> gets(){
    return userRepository.findAll();
   }


}
