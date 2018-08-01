package it.hella.relationship.onetoone.jointable.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Class Address.
 * 
 * <p>
 * One-to-one mapping with foreign key: Address is referenced by a foreign key
 * defined by User.
 * </p>
 * 
 * <b>Note: </b>Here the <b>@OneToOne</b> mapping has a cascade type set to ALL
 * for testing purposes. Normally the cascade type would be defined only on the
 * owning side.
 * 
 */
@Entity(name = "Address_Jt")
public class Address implements Serializable {

	private static final long serialVersionUID = -7085607165735180184L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "ADDRESS_ID")
	private Long id;

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
