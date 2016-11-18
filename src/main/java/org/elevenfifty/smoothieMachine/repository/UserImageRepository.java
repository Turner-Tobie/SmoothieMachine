package org.elevenfifty.smoothieMachine.repository;

import java.util.List;

import org.elevenfifty.smoothieMachine.beans.UserImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageRepository extends CrudRepository<UserImage, Long> {

	List<UserImage> findByUserId(long userId);
}
