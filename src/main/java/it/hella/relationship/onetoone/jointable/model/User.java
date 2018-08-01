package it.hella.relationship.onetoone.jointable.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

/**
 * The Class Address.
 * 
 * <p>
 * One-to-one mapping with foreign key
 * <p>
 * 
 * Well suited for <b>strictly 1-1 mapping so as the foreign key involved in one
 * of the two sides remains non nullable</b> (best practices dictate to avoid
 * nullable foreign keys)
 * 
 */
@Entity(name = "User_Jt")
public class User implements Serializable {

	private static final long serialVersionUID = -7215503915168359357L;

	/** The Id. */
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long Id;

	/** The shipping address. */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ADDRESS", inverseJoinColumns = @JoinColumn(name = "ADR_ID", referencedColumnName = "ADDRESS_ID", unique = true, nullable = false), joinColumns = @JoinColumn(name = "USR_ID", referencedColumnName = "USER_ID"))
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
