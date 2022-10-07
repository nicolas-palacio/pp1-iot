package com.iot.api;

import com.iot.api.sensor.SensorRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Módulo IOT - API", version = "2.0.0"))
@SecurityScheme(name = "BearerJWT",type= SecuritySchemeType.HTTP,scheme = "bearer",bearerFormat ="JWT",description = "Seguridad basda en JWT.")
public class ApiApplication{


	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
