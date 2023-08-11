package com.StateCity.Trip.LogRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StateCity.Trip.LogUser.Role;

public interface UserRoleRepo extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}
