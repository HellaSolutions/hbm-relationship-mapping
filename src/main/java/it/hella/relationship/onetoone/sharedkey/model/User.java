package it.hella.relationship.onetoone.sharedkey.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * The Class User.
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
@Entity(name = "User")
public class User implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8030063083273878785L;

	/** The Id. */
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long Id;

	/** The shipping address. */
	@OneToOne(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Address shippingAddress;

	/** The name. */
	@Column(name = "NAME", nullable = false)
	private String name;

	/** The surname. */
	@Column(name = "SURNAME", nullable = false)
	private String surname;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return Id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.Id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname
	 *            the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the shipping address.
	 *
	 * @return the shipping address
	 */
	public Address getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * Sets the shipping address.
	 *
	 * @param shippingAddress
	 *            the new shipping address
	 */
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

}
