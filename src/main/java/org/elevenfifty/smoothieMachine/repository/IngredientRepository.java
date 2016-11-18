package org.elevenfifty.smoothieMachine.repository;

import java.util.List;


import org.elevenfifty.smoothieMachine.beans.Ingredients;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredients, Long>{

	List<Ingredients> findByIngredientType(String ingredientType);

}
