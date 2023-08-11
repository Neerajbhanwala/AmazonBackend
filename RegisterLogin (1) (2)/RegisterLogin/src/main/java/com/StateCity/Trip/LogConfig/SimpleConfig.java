package com.StateCity.Trip.LogConfig;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.StateCity.Trip.LogJwt.JwtAuthorizationTokenFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SimpleConfig {
	
	@Autowired
	 private SimpleUserDatailsService userDatailsService;
	
	
	@Autowired
	 private JwtAuthorizationTokenFilter authorizationTokenFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf().disable()

	        .authorizeHttpRequests()
	            .requestMatchers("/api/po", "/api/register","/api/login","/art/save","art/getAll").permitAll()
	            .requestMatchers("/api/get").hasRole("ADMIN")
	            .requestMatchers(HttpMethod.POST, "/api/register", "/api/po","/api/login","/art/save","art/getAll").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    
	    http.addFilterBefore(authorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);
	    
	    return http.build();
	}

	
	
	
	           
	    @Bean
	    public JwtAuthorizationTokenFilter jwtTokenFilter() {
	        return new JwtAuthorizationTokenFilter();
	    }
	    
	    @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    @Bean
	     public DaoAuthenticationProvider daoAuthenticationProvider(){
	          DaoAuthenticationProvider daoAuthentication = new DaoAuthenticationProvider();
	          daoAuthentication.setPasswordEncoder(passwordEncoder());
	          daoAuthentication.setUserDetailsService(userDatailsService);
	        return daoAuthentication;
	       }
	    
			       @Bean
			        public AuthenticationManager authentication(AuthenticationConfiguration configuration) throws Exception {
			    	 
			        return (configuration.getAuthenticationManager());
						
					
			   
	      }
}  
     

