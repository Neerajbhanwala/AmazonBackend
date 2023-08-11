package com.StateCity.Trip.LogController;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StateCity.Trip.LogConfig.SimpleUserDatailsService;
import com.StateCity.Trip.LogJwt.JwtHelper;
import com.StateCity.Trip.LogJwt.JwtRequest;
import com.StateCity.Trip.LogJwt.JwtResponse;
import com.StateCity.Trip.LogJwt.LoginRequest;
import com.StateCity.Trip.LogRepository.UserRepository;
import com.StateCity.Trip.LogRepository.UserRoleRepo;
import com.StateCity.Trip.LogService.SimpleService;
import com.StateCity.Trip.LogUser.Users;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtTokenUtil;

    @Autowired
    private SimpleUserDatailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SimpleService simple;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody JwtRequest userDTO) {
        Users users = userRepository.findByMobile(userDTO.getMobile());
        if (userRepository.findByUsername(userDTO.getUsername()) != null || users != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username/Mobile already exists");
        }

        Users user = modelMapper.map(userDTO, Users.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());

        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrMobileOrEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsernameOrMobileOrEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/po")
    public Users add1(@RequestBody Users user) {
        return simple.adding(user);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Users> getting(Users user) {
        return simple.gets();
    }
}
