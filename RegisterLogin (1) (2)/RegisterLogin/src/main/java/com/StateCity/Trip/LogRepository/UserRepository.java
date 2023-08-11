package com.StateCity.Trip.LogRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StateCity.Trip.LogUser.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	Users findByUsername(String username);
	Users findByMobile(String mobile);

}
