package probeaufgabe.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import probeaufgabe.model.entities.CheckAccount;


@Repository("checkAccountsRepository")
public interface CheckAccountsRepository extends CrudRepository<CheckAccount, Integer> {
	
	public List<CheckAccount> findAll();

	public CheckAccount findById(int account_number);
	
	public CheckAccount save(CheckAccount check_account);
	
	public void delete(CheckAccount check_account);
}