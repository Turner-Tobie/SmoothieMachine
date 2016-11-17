package org.elevenfifty.smoothieMachine.security;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

import java.util.List;

import static org.elevenfifty.smoothieMachine.security.UserRole.ADMIN;
import static org.elevenfifty.smoothieMachine.security.UserRole.USER;

import org.elevenfifty.smoothieMachine.beans.Ingredients;
import org.elevenfifty.smoothieMachine.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;




@Service
public class PermissionService {

	@Autowired
	private IngredientRepository ingredientsRepo;
	
	private AbstractAuthenticationToken getToken() {
		return (AbstractAuthenticationToken) getContext().getAuthentication();
	}
	
	public long findCurrentIngredientId() {
		List<Ingredients> ingredients = ingredientsRepo.findByIngredientType(getToken().getName());
		return ingredients != null && !ingredients.isEmpty() ? ingredients.get(0).getId() : -1;
	}
	
	

	public boolean hasRole(UserRole role) {
		for (GrantedAuthority ga : getToken().getAuthorities()) {
			if (role.toString().equals(ga.getAuthority())) {
				return true;
			}
		}
		return false;
	}
}
