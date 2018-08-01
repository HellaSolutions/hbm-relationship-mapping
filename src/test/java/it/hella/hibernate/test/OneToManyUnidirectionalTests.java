package it.hella.hibernate.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.junit.Test;

import it.hella.relationship.onetomany.unidirectional.model.Employee;
import it.hella.relationship.onetomany.unidirectional.model.Project;

public class OneToManyUnidirectionalTests extends BaseTests {

	@Test
	public void oneToManyPersistProjectSideTest() {

		Project project = new Project();
		project.setName("project");

		Employee ea = new Employee();
		ea.setCardId("cardIdA");
		ea.setName("nameA");
		ea.setSurname("surnameA");

		Employee eb = new Employee();
		eb.setCardId("cardIdB");
		eb.setName("nameB");
		eb.setSurname("surnameB");

		project.getEmployees().add(ea);
		project.getEmployees().add(eb);

		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
		entityManager.detach(project);
		entityManager.detach(ea);
		entityManager.detach(eb);

		project = entityManager.find(Project.class, project.getId());
		assertNotNull(project.getEmployees());
		assertEquals(2, project.getEmployees().size());

	}

	@Test
	public void oneToManyRemoveProjectSideTest() {

		Project project = new Project();
		project.setName("projectA");

		Employee ea = new Employee();
		ea.setCardId("cardIdC");
		ea.setName("nameC");
		ea.setSurname("surnameC");

		Employee eb = new Employee();
		eb.setCardId("cardIdD");
		eb.setName("nameD");
		eb.setSurname("surnameD");

		project.getEmployees().add(ea);
		project.getEmployees().add(eb);

		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
		entityManager.detach(project);
		entityManager.detach(ea);
		entityManager.detach(eb);

		project = entityManager.find(Project.class, project.getId());
		project.getEmployees().remove(ea);
		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
		entityManager.detach(project);

		project = entityManager.find(Project.class, project.getId());
		assertNotNull(project.getEmployees());
		assertEquals(1, project.getEmployees().size());

	}

	@Test
	public void oneToManyOrphanRemovaProjectSideTest() {

		thrown.expect(NoResultException.class);

		Project project = new Project();
		project.setName("projectB");

		Employee ea = new Employee();
		ea.setCardId("cardIdE");
		ea.setName("nameE");
		ea.setSurname("surnameE");

		Employee eb = new Employee();
		eb.setCardId("cardIdF");
		eb.setName("nameF");
		eb.setSurname("surnameF");

		project.getEmployees().add(ea);
		project.getEmployees().add(eb);

		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
		entityManager.detach(project);
		entityManager.detach(ea);
		entityManager.detach(eb);

		project = entityManager.find(Project.class, project.getId());
		entityManager.getTransaction().begin();
		entityManager.remove(project);
		entityManager.getTransaction().commit();

		TypedQuery<Employee> eQuery = entityManager.createQuery(
				"select e from EMPLOYEE_OTM_UNI e where e.cardId =:cardId1 or  e.cardId =:cardId2", Employee.class);
		eQuery.setParameter("cardId1", "cardIdE").setParameter("cardId2", "cardIdF").getSingleResult();

	}

}
