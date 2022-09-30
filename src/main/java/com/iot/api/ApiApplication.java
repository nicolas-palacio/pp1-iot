package com.iot.api;

import com.iot.api.sensor.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication{


	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
