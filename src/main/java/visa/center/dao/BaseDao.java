package visa.center.dao;

import java.util.List;

public interface BaseDao<T, ID> {

	ID save(T entity);

	void update(T entity);

	List<T> getAll();

	T getById(ID id);

	void delete(ID id);

}
