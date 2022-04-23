//package co.edu.icesi.dev.uccareapp.transport.service.implementation;
//
//import java.util.Optional;
//
//import co.edu.icesi.dev.uccareapp.transport.model.sales.User;
//import co.edu.icesi.dev.uccareapp.transport.repository.UserRepository;
//import co.edu.icesi.dev.uccareapp.transport.service.interfaces.UserService;
//
//public class UserServiceImp implements UserService {
//	
//	private UserRepository userRepository;
//	
//	public UserServiceImp(UserRepository ur) {
//		userRepository = ur;
//	}
//
//	@Override
//	public void addUser(User user) {
//		if(!searchUserById(user.getId()).isEmpty()) {
//			throw new IllegalArgumentException("ERROR can not exist two users with the same ID");
//		}
//		if(!searchUserByUsername(user.getUsername()).isEmpty()) {
//			throw new IllegalArgumentException("ERROR can not exist two users with the same username");
//		}
//		if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getType()==null) {
//			throw new IllegalArgumentException("ERROR some values are empty");
//		}
//		userRepository.save(user);
//	}
//
//	@Override
//	public Optional<User> searchUserById(Long id) {
//		return userRepository.findById(id);
//	}
//
//	@Override
//	public Optional<User> searchUserByUsername(String username) {
//		Iterable<User> users = userRepository.findAll();
//		for(User user:users) {
//			if(user.getUsername().equals(username)) {
//				return Optional.of(user);
//			}
//		}
//		return null;
//	}
//}
