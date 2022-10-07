package com.kruger.empleado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class ServicioEmpleadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioEmpleadoApplication.class, args);
	}

}
