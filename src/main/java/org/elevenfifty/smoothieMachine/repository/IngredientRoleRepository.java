package org.elevenfifty.smoothieMachine.repository;

import java.util.List;

import org.elevenfifty.smoothieMachine.beans.IngredientRoles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRoleRepository extends CrudRepository<IngredientRoles, Long> {

	List<IngredientRoles> findByIngredientId(long ingredientId);

	
}
