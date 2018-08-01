package it.hella.relationship.ejb;

import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.google.common.base.Optional;

import it.hella.relationship.manytoone.bidirectional.model.Employee;
import it.hella.relationship.manytoone.bidirectional.model.Project;

@Stateless
@Remote(ProjectHandler.class)
public class ProjectBean implements ProjectHandler {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Set<Employee> getEmployees(Project project) {
		if (project.getId() == null) {
			throw new IllegalArgumentException("Unidentified Project entity: id is null");
		}
		Project p = entityManager.find(Project.class, project.getId());
		return p.getEmployees();
	}

	@Override
	public Optional<Project> getProject(String projectName) {
		TypedQuery<Project> query = entityManager.createQuery("select p from PROJECT p where p.name = :pname",
				Project.class);
		try {
			return Optional.of(query.setParameter("pname", projectName).getSingleResult());
		} catch (NoResultException e) {
			return Optional.absent();
		}
	}

}
