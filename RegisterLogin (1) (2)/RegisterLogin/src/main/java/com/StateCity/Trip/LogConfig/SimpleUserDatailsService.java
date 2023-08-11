package com.StateCity.Trip.LogConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.StateCity.Trip.LogRepository.UserRepository;
import com.StateCity.Trip.LogUser.Users;

@Component
public class SimpleUserDatailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrMobileOrEmail) throws UsernameNotFoundException {
        Users user = null;

        // Check if the input is a valid email address
        if (usernameOrMobileOrEmail.contains("@")) {
            user = userRepository.findByUsername(usernameOrMobileOrEmail);
        }

        // If not found by email, check if it's a valid mobile number
        if (user == null && usernameOrMobileOrEmail.matches("\\d{10}")) {
            user = userRepository.findByMobile(usernameOrMobileOrEmail);
        }

        // If not found by email or mobile, try finding by username
        if (user == null) {
            user = userRepository.findByUsername(usernameOrMobileOrEmail);
        }

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username, email, or mobile: " + usernameOrMobileOrEmail);
        }

        return new SimpleUserDetails(user);
    }
}
