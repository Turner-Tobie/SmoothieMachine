package org.elevenfifty.smoothieMachine.repository;

import org.elevenfifty.smoothieMachine.beans.IngredientsVeggies;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VegetablesRepository extends CrudRepository<IngredientsVeggies, Long>{

}
