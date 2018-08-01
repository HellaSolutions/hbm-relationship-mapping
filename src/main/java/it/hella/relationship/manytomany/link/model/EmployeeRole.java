package it.hella.relationship.manytomany.link.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "EMPLOYEE_ROLE")
public class EmployeeRole implements Serializable {
	private static final long serialVersionUID = -8814476707724468762L;

	@Id
	@ManyToOne
	private Employee employee;

	@Id
	@ManyToOne
	private Role role;

	public EmployeeRole() {
	}

	public EmployeeRole(Employee employee, Role role) {
		this.employee = employee;
		this.role = role;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(role, employee);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		EmployeeRole that = (EmployeeRole) obj;
		return Objects.equals(employee, that.employee) && Objects.equals(role, that.role);
	}

}
