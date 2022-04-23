package co.edu.icesi.dev.uccareapp.transport.model.sales;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank
	@Size(min = 2)
	private String username;

	@NotBlank
	@NotNull
	@Size(min = 8)
	private String password;
	
	@NotNull
	private UserType type;
}