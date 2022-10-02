package com.iot.api;

import com.iot.api.sensor.SensorRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Módulo IOT - API", version = "1.0.0"))
public class ApiApplication{


	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
