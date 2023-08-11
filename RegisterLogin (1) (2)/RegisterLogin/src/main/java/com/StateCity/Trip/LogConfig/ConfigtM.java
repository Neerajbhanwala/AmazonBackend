package com.StateCity.Trip.LogConfig;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigtM {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
