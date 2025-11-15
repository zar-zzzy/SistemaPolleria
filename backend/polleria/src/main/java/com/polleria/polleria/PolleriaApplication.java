package com.polleria.polleria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación.
 * Inicia el servidor Spring Boot con Tomcat embebido en el puerto 8080.
 */
@SpringBootApplication
public class PolleriaApplication {

	/**
	 * Método principal que arranca la aplicación.
	 * Ejecutar: mvn spring-boot:run
	 */
	public static void main(String[] args) {
		SpringApplication.run(PolleriaApplication.class, args);
	}

}
