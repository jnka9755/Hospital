package co.edu.unicundi.proyectoSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProyectoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoSpringApplication.class, args);
	}

}
