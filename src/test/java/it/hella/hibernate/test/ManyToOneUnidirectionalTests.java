package it.hella.hibernate.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.hella.relationship.manytoone.unidirectional.model.Employee;
import it.hella.relationship.manytoone.unidirectional.model.Project;

public class ManyToOneUnidirectionalTests extends BaseTests {

	@Test
	public void manyToManyPersistEmployeeSideTest() {

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
		entityManager.detach(project);
		entityManager.detach(ea);
		entityManager.detach(eb);

		ea = entityManager.find(Employee.class, ea.getId());
		assertEquals(project.getId(), ea.getProject().getId());
		eb = entityManager.find(Employee.class, eb.getId());
		assertEquals(project.getId(), eb.getProject().getId());

	}

	@Test
	public void lifeCycleTest() {

		entityManager.getTransaction().begin();

		Project project = new Project();
		project.setName("projectB");
		entityManager.persist(project);

		Employee ea = new Employee();
		ea.setCardId("cardIdC");
		ea.setName("nameC");
		ea.setSurname("surnameC");
		ea.setProject(project);
		entityManager.persist(ea);

		entityManager.flush();
		ea.setProject(null);
		entityManager.getTransaction().commit();

	}

}
