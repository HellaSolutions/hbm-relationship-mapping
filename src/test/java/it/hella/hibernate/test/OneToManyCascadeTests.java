package it.hella.hibernate.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.hella.relationship.manytoone.bidirectional.cascade.model.Employee;
import it.hella.relationship.manytoone.bidirectional.cascade.model.Project;

public class OneToManyCascadeTests extends BaseTests {

	/**
	 * <p>
	 * Removing the directive <b>mappedBy = "project"</b> from the <b>@ManyToOne
	 * side</b> this test fails.
	 * </p>
	 * 
	 * The JPA runtime creates a relation table project_employee which holds the
	 * pairs (project_id, empoyee_id) and that is handled only by the Project
	 * side, i.e. by adding an Employee to the Project.employees collection.
	 * <br/>
	 * 
	 **/
	@Test
	public void oneToManyPersistEmployeeSideTest() {

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

		entityManager.getTransaction().begin();
		project = entityManager.find(Project.class, project.getId());
		assertEquals(2, project.getEmployees().size());
		entityManager.getTransaction().commit();

	}

	@Test
	public void oneToManyPersistProjectSideTest() {

		Project project = new Project();
		project.setName("projectB");

		Employee ea = new Employee();
		ea.setCardId("cardIdC");
		ea.setName("nameC");
		ea.setSurname("surnameC");
		project.addEmployee(ea);

		Employee eb = new Employee();
		eb.setCardId("cardIdD");
		eb.setName("nameD");
		eb.setSurname("surnameD");
		project.addEmployee(eb);

		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
		entityManager.detach(project);

		entityManager.getTransaction().begin();
		project = entityManager.find(Project.class, project.getId());
		assertEquals(2, project.getEmployees().size());
		entityManager.getTransaction().commit();

	}

	@Test
	public void oneToManyPersistCollectionManagedTest() {

		Project project = new Project();
		project.setName("projectC");

		Employee ea = new Employee();
		ea.setCardId("cardIdE");
		ea.setName("nameE");
		ea.setSurname("surnameE");
		project.getEmployees().add(ea);

		Employee eb = new Employee();
		eb.setCardId("cardIdF");
		eb.setName("nameF");
		eb.setSurname("surnameF");
		project.getEmployees().add(eb);

		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
		entityManager.detach(project);

		entityManager.getTransaction().begin();
		project = entityManager.find(Project.class, project.getId());
		assertEquals(2, project.getEmployees().size());
		entityManager.getTransaction().commit();

	}

}
