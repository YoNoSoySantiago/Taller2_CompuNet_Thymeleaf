package co.edu.icesi.dev.uccareapp.transport;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.dev.uccareapp.transport.model.sales.UserApp;
import co.edu.icesi.dev.uccareapp.transport.model.sales.UserType;
import co.edu.icesi.dev.uccareapp.transport.repository.UserRepository;

@SpringBootApplication
public class Taller1ShApplication {

	public static void main(String[] args) {
		SpringApplication.run(Taller1ShApplication.class, args);
	}
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	@Bean
	public CommandLineRunner clr(UserRepository userRepository) {
		return (args->{
			UserApp userAdmin = new UserApp();
			userAdmin.setUsername("YoNoSoySantiago");
			userAdmin.setPassword("1234");
			userAdmin.setType(UserType.admin);
			
			UserApp userOp = new UserApp();
			userOp.setUsername("Cpasuy06");
			userOp.setPassword("4321");
			userOp.setType(UserType.operator);
			
			userRepository.save(userAdmin);
			userRepository.save(userOp);
		});
	}
}
