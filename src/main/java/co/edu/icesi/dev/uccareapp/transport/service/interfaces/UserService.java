package co.edu.icesi.dev.uccareapp.transport.service.interfaces;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.sales.User;

public interface UserService {
	public void addUser(User user);
	public Optional<User> searchUserById(Long id);
	public Optional<User> searchUserByUsername(String username);
	
}
