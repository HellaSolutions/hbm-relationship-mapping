package it.hella.relationship.manytomany.bidirectional.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;

import it.hella.relationship.util.MergeCollection;

/**
 * <p>
 * The Class Project.
 * </p>
 *
 * Implements the many part of a many-to-one bidirectional association.
 * 
 * @see #OneToManyBidirectionalTests
 */
@Entity(name = "ROLE_MTM_BID")
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

	@ManyToMany(cascade = { CascadeType.PERSIST })
	@JoinTable(name = "EMPLOYEE_ROLE", joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"EMPLOYEE_ID", "ROLE_ID" }))
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
	public void setEmployees(Set<Employee> employees) {
		this.employees.clear();
		this.employees.addAll(employees);
	}

	@SuppressWarnings("unused")
	public void mergeEmployees(Set<Employee> employees, EntityManager em) {
		MergeCollection.merge(this.employees, employees, em);
	}

	public void addEmployee(Employee employee) {
		if (employees.contains(employee)) {
			return;
		}
		employees.add(employee);
		employee.getRoles().add(this);
	}

	public void removeEmployee(Employee employee) {
		if (!employees.contains(employee)) {
			return;
		}
		employees.remove(employee);
		employee.getRoles().remove(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
