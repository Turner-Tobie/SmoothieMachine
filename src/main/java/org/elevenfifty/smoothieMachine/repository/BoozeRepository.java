package org.elevenfifty.smoothieMachine.repository;

import org.elevenfifty.smoothieMachine.beans.IngredientsAlcohol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoozeRepository extends CrudRepository<IngredientsAlcohol, Long>{

}
