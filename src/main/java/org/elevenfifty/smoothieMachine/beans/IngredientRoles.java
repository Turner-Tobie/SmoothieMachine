package org.elevenfifty.smoothieMachine.beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.elevenfifty.smoothieMachine.security.IngredientRole;

@Entity
@Table(name="ingredient_roles")
public class IngredientRoles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(unique=true)
	private long ingredientId;
	
	private String role;
	
	public IngredientRoles(){
		
	}
	
	
	public IngredientRoles(Ingredients ingredient, IngredientRole role ){
		this.ingredientId = ingredient.getId();
		this.role = role.toString().substring(role.toString().indexOf('_') + 1);
	}
	
	public IngredientRoles(long ingredientId, String role){
		this.ingredientId = ingredientId;
		this.role = role;
	}

		
	@Override
	public String toString() {
		return "IngredientRoles [id=" + id + ", ingredientId=" + ingredientId + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IngredientRoles other = (IngredientRoles) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(long ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
}
