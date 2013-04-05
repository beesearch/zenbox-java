package fr.zen.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The persistent class for the ZN_USER database table.
 * 
 */
@Entity
@SuppressWarnings("serial")
public class ZnUser implements Serializable {

	@Id
	@GeneratedValue
	private int id;

	private String firstName;

	private String lastName;

	public ZnUser() {
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
