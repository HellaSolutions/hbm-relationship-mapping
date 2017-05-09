package it.hella.relationship.ejb;

import java.util.Set;

import com.google.common.base.Optional;

import it.hella.relationship.model.Employee;
import it.hella.relationship.model.Project;

public interface ProjectHandler {

	public Set<Employee> getEmployees(Project project);

	public Optional<Project> getProject(String projectName);

}
