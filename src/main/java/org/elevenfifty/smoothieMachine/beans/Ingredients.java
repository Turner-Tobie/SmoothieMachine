package org.elevenfifty.smoothieMachine.beans;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.mapping.Array;

@Entity
@Table(name = "ingredients")
public class Ingredients<ingredientType, hasIngredientType> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Size(max = 128)
	private String name;

	@DecimalMin(value = "0", message = "")
	@DecimalMax(value = "100", message = "")
	private BigDecimal price;

	@Size(max = 45)
	private String vitamins;
	
	@Min(value = 0, message = "")	
	private int calories;
	
	@Size(max= 20)
	private String ingredientType;
	
	private boolean instock;
	
	private boolean hasIngredientType;

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
		Ingredients other = (Ingredients) obj;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getVitamins() {
		return vitamins;
	}

	public void setVitamins(String vitamins) {
		this.vitamins = vitamins;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}
	
	public String getIngredientType() {		
		return ingredientType;
		
	}

	public boolean isHasIngredientType() {
		if(this.ingredientType != null){
		return true;
		}else{
			hasIngredientType=true;
			return isHasIngredientType();
		}
	}

	public void setIngredientType(String ingredientType) {
		this.ingredientType = ingredientType;
	}

	public boolean isInstock() {
		return instock;
	}

	public void setInstock(boolean instock) {
		this.instock = instock;
	}

	
	
	
}
