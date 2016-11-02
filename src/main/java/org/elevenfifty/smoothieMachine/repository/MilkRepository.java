package org.elevenfifty.smoothieMachine.repository;

import org.elevenfifty.smoothieMachine.beans.IngredientsMilk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilkRepository extends CrudRepository<IngredientsMilk, Long>{

}
