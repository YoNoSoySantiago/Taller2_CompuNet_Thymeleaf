package co.edu.icesi.dev.uccareapp.transport.service.interfaces;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.sales.UserApp;

public interface UserService {
	public void addUser(UserApp user);
	public Optional<UserApp> searchUserById(Long id);
	public Optional<UserApp> searchUserByUsername(String username);
	
}
