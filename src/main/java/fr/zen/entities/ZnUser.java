package fr.zen.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ZN_USER database table.
 * 
 */
@Entity
@Table(name="ZN_USER")
public class ZnUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
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
