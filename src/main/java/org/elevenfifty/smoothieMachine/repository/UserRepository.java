package org.elevenfifty.smoothieMachine.repository;

import org.elevenfifty.smoothieMachine.beans.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

}
