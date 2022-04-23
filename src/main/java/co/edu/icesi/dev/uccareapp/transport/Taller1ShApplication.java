package co.edu.icesi.dev.uccareapp.transport;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import co.edu.icesi.dev.uccareapp.transport.model.sales.User;
import co.edu.icesi.dev.uccareapp.transport.model.sales.UserType;
import co.edu.icesi.dev.uccareapp.transport.repository.UserRepository;

@SpringBootApplication
public class Taller1ShApplication {

	public static void main(String[] args) {
		SpringApplication.run(Taller1ShApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner clr(UserRepository userRepository) {
		return (args->{
			User userAdmin = new User();
			userAdmin.setUsername("YoNoSoySantiago");
			userAdmin.setPassword("1234");
			userAdmin.setType(UserType.admin);
			
			User userOp = new User();
			userOp.setUsername("Cpasuy06");
			userOp.setPassword("4321");
			userOp.setType(UserType.operator);
			
			userRepository.save(userAdmin);
			userRepository.save(userOp);
		});
	}
}
