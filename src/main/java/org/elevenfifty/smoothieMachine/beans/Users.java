package org.elevenfifty.smoothieMachine.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Size(max = 45)
	private String firstName;

	@Size(max = 45)
	private String lastName;

	@Column(unique = true)
	@Size(max = 128)
	private String email;

	@Size(max = 45)
	private String password;

	@Size(max = 10)
	private String phoneNumber;

	private boolean active;

	public Users() {
		active = true;
	}

	public Users(String firstName, String lastName, String email, String phoneNumber, String password, boolean active) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.active = active;
	}
	
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", phoneNumber=" + phoneNumber + ", active=" + active + "]";
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fistName) {
		this.firstName = fistName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
