package visa.center.dao.impl;

import org.springframework.stereotype.Repository;

import visa.center.dao.UserDao;
import visa.center.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public User findByUsername(String username) {
		User user = (User) getCurrentSession().createQuery("from User where username = :username")
				.setParameter("username", username).uniqueResult();
		return user;
	}

}
