package org.elevenfifty.smoothieMachine.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients_properties")
public class IngredientsProperties {

	

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		long id;

		@Column(unique = true)
		long userId;

		String propName;
		String propValue;
		
		
		
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
			IngredientsProperties other = (IngredientsProperties) obj;
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
		public long getUserId() {
			return userId;
		}
		public void setUserId(long userId) {
			this.userId = userId;
		}
		public String getPropName() {
			return propName;
		}
		public void setPropName(String propName) {
			this.propName = propName;
		}
		public String getPropValue() {
			return propValue;
		}
		public void setPropValue(String propValue) {
			this.propValue = propValue;
		}
		
		
}
