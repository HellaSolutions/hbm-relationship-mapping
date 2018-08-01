package it.hella.hibernate.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import it.hella.relationship.manytomany.link.model.Employee;
import it.hella.relationship.manytomany.link.model.Role;

public class ManyToManyLinkTests extends BaseTests {

	@Test
	public void manyToManyPersistTransientAllRoleSideTest() {

		Role developerRole = new Role();
		developerRole.setName("DEVELOPER");

		Role projectManagerRole = new Role();
		projectManagerRole.setName("PROJECT_MANAGER");

		Employee ea = new Employee();
		ea.setCardId("cardIdA");
		ea.setName("nameA");
		ea.setSurname("surnameA");

		Employee eb = new Employee();
		eb.setCardId("cardIdB");
		eb.setName("nameB");
		eb.setSurname("surnameB");

		entityManager.getTransaction().begin();

		entityManager.persist(developerRole);
		entityManager.persist(projectManagerRole);
		entityManager.persist(ea);
		entityManager.persist(eb);

		developerRole.addEmployee(ea);
		developerRole.addEmployee(eb);
		projectManagerRole.addEmployee(eb);

		entityManager.getTransaction().commit();
		entityManager.clear();

		Role mergedDeveloperRole = entityManager.find(Role.class, developerRole.getId());
		assertNotNull(mergedDeveloperRole.getEmployees());
		assertEquals(2, mergedDeveloperRole.getEmployees().size());

		Role mergedProjectManagerRole = entityManager.find(Role.class, projectManagerRole.getId());
		assertNotNull(mergedProjectManagerRole.getEmployees());
		assertEquals(1, mergedProjectManagerRole.getEmployees().size());

		Employee mergedEa = entityManager.find(Employee.class, ea.getId());
		assertNotNull(mergedEa.getRoles());
		assertEquals(1, mergedEa.getRoles().size());

		Employee mergedEb = entityManager.find(Employee.class, eb.getId());
		assertNotNull(mergedEb.getRoles());
		assertEquals(2, mergedEb.getRoles().size());

	}

	@Test
	public void manyToManyPersistEmployeeSideTest() {

		// Persist related objects
		Role developerRole = new Role();
		developerRole.setName("DEVELOPER_B");

		Role projectManagerRole = new Role();
		projectManagerRole.setName("PROJECT_MANAGER_B");

		Employee ea = new Employee();
		ea.setCardId("cardIdC");
		ea.setName("nameC");
		ea.setSurname("surnameC");

		Employee eb = new Employee();
		eb.setCardId("cardIdD");
		eb.setName("nameD");
		eb.setSurname("surnameD");

		entityManager.getTransaction().begin();

		entityManager.persist(developerRole);
		entityManager.persist(projectManagerRole);
		entityManager.persist(ea);
		entityManager.persist(eb);

		entityManager.getTransaction().commit();
		entityManager.clear();

		// re-attach and update roles
		entityManager.getTransaction().begin();

		developerRole = entityManager.merge(developerRole);
		developerRole.addEmployee(entityManager.merge(ea));
		eb = entityManager.merge(eb);
		developerRole.addEmployee(eb);
		projectManagerRole = entityManager.merge(projectManagerRole);
		projectManagerRole.addEmployee(eb);

		entityManager.getTransaction().commit();
		entityManager.clear();

		// verify data integrity

		Role mergedDeveloperRole = entityManager.find(Role.class, developerRole.getId());
		assertNotNull(mergedDeveloperRole.getEmployees());
		assertEquals(2, mergedDeveloperRole.getEmployees().size());

		Role mergedProjectManagerRole = entityManager.find(Role.class, projectManagerRole.getId());
		assertNotNull(mergedProjectManagerRole.getEmployees());
		assertEquals(1, mergedProjectManagerRole.getEmployees().size());

		Employee mergedEa = entityManager.find(Employee.class, ea.getId());
		assertNotNull(mergedEa.getRoles());
		assertEquals(1, mergedEa.getRoles().size());

		Employee mergedEb = entityManager.find(Employee.class, eb.getId());
		assertNotNull(mergedEb.getRoles());
		assertEquals(2, mergedEb.getRoles().size());

	}

}
