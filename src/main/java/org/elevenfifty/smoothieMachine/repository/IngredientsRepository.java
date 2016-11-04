package org.elevenfifty.smoothieMachine.repository;

import org.elevenfifty.smoothieMachine.beans.Ingredients;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepository extends CrudRepository<Ingredients, Long>{


}
