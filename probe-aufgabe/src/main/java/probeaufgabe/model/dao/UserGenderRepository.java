package probeaufgabe.model.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import probeaufgabe.model.entities.UserGender;

@Repository("userGenderRepository")
public interface UserGenderRepository extends CrudRepository<UserGender, Integer> {
	
	public List<UserGender> findAll();

	public UserGender findById(int gender_id);
	
	public UserGender findByInsertkey(char genderKey);
}