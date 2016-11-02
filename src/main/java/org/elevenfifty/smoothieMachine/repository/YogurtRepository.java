package org.elevenfifty.smoothieMachine.repository;

import org.elevenfifty.smoothieMachine.beans.IngredientsYogurt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YogurtRepository extends CrudRepository<IngredientsYogurt, Long>{

}
