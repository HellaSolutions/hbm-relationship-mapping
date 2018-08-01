package it.hella.relationship.manytomany.link.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

/**
 * <p>
 * The Class Employee.
 * </p>
 *
 * Implements the one part of a many-to-one bidirectional association.
 * 
 * @see #OneToManyBidirectionalTests
 */
@Entity(name = "EMPLOYEE_MTM_JOIN")
public class Employee implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8180506443708481408L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "EMPLOYEE_ID")
	private Long id;

	/** The name. */
	@Column(name = "NAME", nullable = false)
	private String name;

	/** The surname. */
	@Column(name = "SURNAME", nullable = false)
	private String surname;

	/** The card id. */
	@NaturalId
	@Column(name = "CARD_ID")
	private String cardId;

	@OneToMany(mappedBy = "employee", orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<EmployeeRole> employeeRoles = new HashSet<>();

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
	 * Gets the card id.
	 *
	 * @return the card id
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * Sets the card id.
	 *
	 * @param cardId
	 *            the new card id
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public void addRole(Role role) {
		EmployeeRole er = new EmployeeRole(this, role);
		if (employeeRoles.contains(er)) {
			return;
		}
		employeeRoles.add(er);
		role.getEmployeeRoles().add(er);
	}

	public void removeRole(Role role) {
		EmployeeRole er = new EmployeeRole(this, role);
		if (!employeeRoles.contains(er)) {
			return;
		}
		employeeRoles.remove(er);
		role.getEmployeeRoles().remove(er);
		er.setEmployee(null);
		er.setRole(null);
	}

	// public List<Role> getRoles() {
	// return
	// employeeRoles.stream().map(EmployeeRole::getRole).collect(Collectors.toList());
	// }

	public Set<EmployeeRole> getRoles() {
		return employeeRoles;
	}

	protected void setEmployeeRoles(Set<EmployeeRole> employeeRoles) {
		this.employeeRoles = employeeRoles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(cardId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Object o = Objects.requireNonNull(obj);
		return Objects.equals(this.cardId, ((Employee) o).cardId);
	}

}
