package it.hella.relationship.onetoone.sharedkey.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 * The Class Address.
 * 
 * <p>
 * One-to-one mapping with shared key: Address inherits the primary key from
 * User.
 * </p>
 * 
 * Shared primary key works well for <b>0..1-1..1 associations, where the
 * primary key is dictated by the 0..1 part</b>. In our case, User can have 0 or
 * 1 addresses, while each address should have exactly one user.
 * 
 */
@Entity(name = "Address")
public class Address implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7528964012027540580L;

	/** The id. */
	@Id
	private Long id;

	@JoinColumn(name = "ID")
	@OneToOne
	@MapsId
	private User user;

	/** The street. */
	@Column(name = "ADDRESS_STREET", nullable = false)
	private String street;

	/** The zipcode. */
	@Column(name = "ADDRESS_ZIPCODE", nullable = false)
	private String zipcode;

	/** The city. */
	@Column(name = "ADDRESS_CITY", nullable = false)
	private String city;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Sets the street.
	 *
	 * @param street
	 *            the new street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Gets the zipcode.
	 *
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * Sets the zipcode.
	 *
	 * @param zipcode
	 *            the new zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city
	 *            the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

}
