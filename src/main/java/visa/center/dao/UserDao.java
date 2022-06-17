package visa.center.dao;

import visa.center.model.User;

public interface UserDao extends BaseDao<User, Long> {

	User findByUsername(String username);
}
