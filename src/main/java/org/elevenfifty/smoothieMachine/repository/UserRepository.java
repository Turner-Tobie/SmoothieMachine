package org.elevenfifty.smoothieMachine.repository;

import java.util.List;

import org.elevenfifty.smoothieMachine.beans.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

	List<Users> findByLastNameOrFirstNameOrEmailIgnoreCase(
			String lastName, String firstName, String email);
	
	

	List<Users> findByEmail(String email);

	List<Users> findAllByOrderByFirstNameAscLastNameAsc();

}
