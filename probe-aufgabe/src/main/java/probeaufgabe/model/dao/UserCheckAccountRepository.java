package probeaufgabe.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import probeaufgabe.model.entities.UserCheckAccount;
import probeaufgabe.model.identity.UserCheckAccountIdentity;


@Repository("userCheckAccountRepository")
public interface UserCheckAccountRepository extends CrudRepository<UserCheckAccount, UserCheckAccountIdentity> {
	
	public List<UserCheckAccount> findAll();

}