package it.hella.relationship.manytomany.link.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
@Entity(name = "ROLE_MTM_JOIN")
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

	@OneToMany(mappedBy = "role", orphanRemoval = true)
	private Set<EmployeeRole> employees = new HashSet<>();

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

	@SuppressWarnings("unused")
	public void setEmployeeRoles(Set<EmployeeRole> employees) {
		this.employees = employees;
	}

	public void addEmployee(Employee employee) {
		EmployeeRole er = new EmployeeRole(employee, this);
		if (employees.contains(er)) {
			return;
		}
		employees.add(er);
		employee.getRoles().add(er);
	}

	public void removeEmployee(Employee employee) {
		EmployeeRole er = new EmployeeRole(employee, this);
		if (!employees.contains(er)) {
			return;
		}
		employees.remove(er);
		employee.getRoles().remove(er);
		er.setEmployee(null);
		er.setRole(null);
	}

	public Set<EmployeeRole> getEmployees() {
		return employees;
	}

	// public List<Employee> getEmployees() {
	// return
	// employees.stream().map(EmployeeRole::getEmployee).collect(Collectors.toList());
	// }

	public void setEmployees(Set<EmployeeRole> employees) {
		this.employees.clear();
		this.employees.addAll(employees);
	}

	public Set<EmployeeRole> getEmployeeRoles() {
		return employees;
	}

	public void mergeEmployees(Set<EmployeeRole> employees, EntityManager em) {
		MergeCollection.merge(this.employees, employees, em);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}

	@Override
	public boolean equals(Object obj) {
		Object o = Objects.requireNonNull(obj);
		return Objects.equals(this.name, ((Role) o).name);
	}

}
