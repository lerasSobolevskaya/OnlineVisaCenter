package visa.center.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import visa.center.model.User;

public interface UserService extends UserDetailsService {

	List<User> getAllUsers();

	User getUserById(Long id);

	void deleteUser(Long id);

	void updateUser(User user);

	User findByUsername(String username);

}
