package fr.zen.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the ZnUser database table.
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "ZnUser.list", query = "select u from ZnUser u")
})
public class ZnUser {

	@Id
	@GeneratedValue
	private long id;

	//@NotNull
	//@Size(min = 3, max = 15)
	private String firstName;

	//@NotNull
	//@Size(min = 3, max = 15)
	private String lastName;

	//@NotNull
	//@Size(min = 4, max = 15)
	private String password;

	//@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String email;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
