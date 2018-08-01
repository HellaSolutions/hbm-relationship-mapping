package it.hella.relationship.manytoone.bidirectional.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * <p>
 * The Class Project.
 * </p>
 *
 * Implements the many part of a many-to-one bidirectional association.
 * 
 * @see #OneToManyBidirectionalTests
 */
@Entity(name = "PROJECT")
public class Project implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5811823280390431611L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "PROJECT_ID")
	private Long id;

	/** The name. */
	@Column(name = "NAME")
	private String name;

	/** The employees. */
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
	private Set<Employee> employees;

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
	 * Gets the employees.
	 *
	 * @return the employees
	 */
	public Set<Employee> getEmployees() {
		if (employees == null) {
			employees = new HashSet<>();
		}
		return employees;
	}

	/**
	 * Sets the employees.
	 *
	 * @param employees
	 *            the new employees
	 */
	@SuppressWarnings("unused")
	private void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	/**
	 * Adds the employee.
	 *
	 * @param e
	 *            the e
	 */
	public void addEmployee(Employee e) {
		e.setProject(this);
		if (employees == null) {
			employees = new HashSet<>();
		}
		employees.add(e);
	}

	/**
	 * Removes the employee.
	 *
	 * @param e
	 *            the e
	 */
	public void removeEmployee(Employee e) {
		if (employees != null && employees.remove(e)) {
			e.setProject(null);
		}
	}

}
