package probeaufgabe.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import probeaufgabe.model.entities.User;


@Repository("usersRepository")
public interface UsersRepository extends CrudRepository<User, Integer> {
	
	public List<User> findAll();

	public User findById(int user_number);
	
	public User save(User user);
	
	public void delete(User user);
}