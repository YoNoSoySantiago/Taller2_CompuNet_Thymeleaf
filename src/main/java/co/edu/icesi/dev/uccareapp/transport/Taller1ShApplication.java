package co.edu.icesi.dev.uccareapp.transport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class Taller1ShApplication {

	public static void main(String[] args) {
		SpringApplication.run(Taller1ShApplication.class, args);
	}

}
