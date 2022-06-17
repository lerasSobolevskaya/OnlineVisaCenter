package visa.center.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import visa.center.dao.UserDao;
import visa.center.model.User;
import visa.center.model.enums.Role;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{

	private UserDao userDao;

	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = userDao.getAll();
		log.info("IN getAllUsers() - {} users found ", users.size());
		return users;
	}

	@Override
	public User getUserById(Long id) {
		User user = userDao.getById(id);
		if (user == null) {
			log.warn("IN getUserById - user: {} found by id {}", id);
		}
		log.info("IN getUserById - user: {} found by id {}", user);

		return user;
	}

	@Override
	public void deleteUser(Long id) {
		if (id != null) {
			userDao.delete(id);
		}
		throw new NoSuchElementException("There is no user with id: " + id);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);

	}

	@Override
	public User findByUsername(String username) {
		User user = userDao.findByUsername(username);
		if (user == null) {
			log.warn("IN findByUsername - user: {} found by username {}", username);
		}
		log.info("IN findByUsername - user: {} found by username {}", user);

		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		Set<GrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority(Role.ROLE_ADMIN.name()));
		roles.add(new SimpleGrantedAuthority(Role.ROLE_USER.name()));
		if (user == null) {
			throw new UsernameNotFoundException("User with name " + username + " not found");
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(),
				roles);
		log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);

		return userDetails;
	}

}
