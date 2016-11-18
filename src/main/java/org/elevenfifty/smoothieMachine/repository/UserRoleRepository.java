package org.elevenfifty.smoothieMachine.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.elevenfifty.smoothieMachine.beans.UserRoles;

public interface UserRoleRepository extends CrudRepository<UserRoles, Long> {

	List<UserRoles> findByUserId(long userId);
	
}
