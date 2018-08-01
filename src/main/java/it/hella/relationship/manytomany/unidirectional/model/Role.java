package it.hella.relationship.manytomany.unidirectional.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.NaturalId;

/**
 * <p>
 * The Class Project.
 * </p>
 *
 * Implements the many part of a many-to-one bidirectional association.
 * 
 * @see #OneToManyBidirectionalTests
 */
@Entity(name = "ROLE_MTM_UNI")
public class Role implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5811823280390431611L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "ROLE_ID")
	private Long id;

	@NaturalId
	@Column(name = "NAME")
	private String name;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Employee> employees = new HashSet<>();

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

	public Set<Employee> getEmployees() {
		return employees;
	}

	@SuppressWarnings("unused")
	private void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}
