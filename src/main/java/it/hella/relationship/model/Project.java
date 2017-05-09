package it.hella.relationship.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "PROJECT")
public class Project implements Serializable {
	private static final long serialVersionUID = -5811823280390431611L;

	@Id
	@GeneratedValue
	@Column(name = "PROJECT_ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
	private Set<Employee> employees; // = new HashSet<>();

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

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

	public void addEmployee(Employee e) {
		e.setProject(this);
	}

	public void removeEmployee(Employee e) {
		e.setProject(null);
	}

}
