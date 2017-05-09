package it.hella.hibernate.test;

import org.junit.Test;

import it.hella.relationship.model.Employee;
import it.hella.relationship.model.Project;

public class OneToManyTests extends BaseTests {

	@Test
	public void oneToManyEmployeeSideTest() {

		Project project = new Project();
		project.setName("project");

		Employee ea = new Employee();
		ea.setCardId("cardIdA");
		ea.setName("nameA");
		ea.setSurname("surnameA");
		ea.setProject(project);

		Employee eb = new Employee();
		eb.setCardId("cardIdB");
		eb.setName("nameB");
		eb.setSurname("surnameB");
		eb.setProject(project);

		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.persist(ea);
		entityManager.persist(eb);
		entityManager.getTransaction().commit();

		// entityManager.getTransaction().begin();
		// project = entityManager.find(Project.class, project.getId());
		// logger.info("assigned employees > " + project.getEmployees().size());
		// entityManager.getTransaction().commit();

	}

}
