package org.elevenfifty.smoothieMachine.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.elevenfifty.smoothieMachine.beans.UserRoles;
import org.elevenfifty.smoothieMachine.security.UserRole;


@Entity
@Table(name = "user_roles")
public class UserRoles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	@Column(unique=true)
	long userId;
	
	String role;
	
	public UserRoles(Users user, UserRole role) {
		this.userId = user.getId();
		this.role = role.toString().substring(role.toString().indexOf('_') + 1);
	}


	public UserRoles(long userId, String role) {
		this.userId = userId;
		this.role = role;
	}

	
	@Override
	public String toString() {
		return "UserRoles [id=" + id + ", userId=" + userId + ", role=" + role + "]";
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
		UserRoles other = (UserRoles) obj;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}