package it.hella.hibernate.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import it.hella.relationship.manytomany.bidirectional.model.Employee;
import it.hella.relationship.manytomany.bidirectional.model.Role;

public class ManyToManyBidirectionalTests extends BaseTests {

	@Test
	public void manyToManyBidirectionalTransientAllTest() {

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

		ea = entityManager.find(Employee.class, ea.getId());
		assertNotNull(ea.getRoles());
		assertEquals(1, ea.getRoles().size());

		eb = entityManager.find(Employee.class, eb.getId());
		assertNotNull(eb.getRoles());
		assertEquals(2, eb.getRoles().size());

	}

	@Test
	public void manyToManyBidirectionalDetachedAllBuildTest() {

		// Persist related objects
		Role developerRole = new Role();
		developerRole.setName("DEVELOPER_B");

		Role projectManagerRole = new Role();
		projectManagerRole.setName("PROJECT_MANAGER_B");

		entityManager.getTransaction().begin();

		entityManager.persist(developerRole);
		entityManager.persist(projectManagerRole);

		entityManager.getTransaction().commit();
		entityManager.clear();

		Employee ea = new Employee();
		ea.setCardId("cardIdC");
		ea.setName("nameC");
		ea.setSurname("surnameC");

		Employee eb = new Employee();
		eb.setCardId("cardIdD");
		eb.setName("nameD");
		eb.setSurname("surnameD");

		entityManager.getTransaction().begin();

		entityManager.persist(ea);
		entityManager.persist(eb);
		entityManager.merge(developerRole).addEmployee(ea);

		entityManager.getTransaction().commit();
		entityManager.clear();

		// detached roles updates
		developerRole.removeEmployee(ea);
		developerRole.addEmployee(eb);
		projectManagerRole.addEmployee(eb);

		// re-attach and update roles
		entityManager.getTransaction().begin();
		Role mergedDeveloperRole = entityManager.merge(developerRole);
		Set<Employee> employees = developerRole.getEmployees().stream().map(e -> {
			if (entityManager.contains(e)) {
				return e;
			}
			if (e.getId() != null) {
				return entityManager.merge(e);
			}
			return e;
		}).collect(Collectors.toSet());
		mergedDeveloperRole.setEmployees(employees);

		Role mergedProjectManagerRole = entityManager.merge(projectManagerRole);
		Set<Employee> pmEmployees = projectManagerRole.getEmployees().stream().map(e -> {
			if (entityManager.contains(e)) {
				return e;
			}
			if (e.getId() != null) {
				return entityManager.merge(e);
			}
			return e;
		}).collect(Collectors.toSet());
		mergedProjectManagerRole.setEmployees(pmEmployees);

		entityManager.getTransaction().commit();
		entityManager.clear();

		// verify data integrity

		mergedDeveloperRole = entityManager.find(Role.class, developerRole.getId());
		assertNotNull(mergedDeveloperRole.getEmployees());
		assertEquals(1, mergedDeveloperRole.getEmployees().size());

		mergedProjectManagerRole = entityManager.find(Role.class, projectManagerRole.getId());
		assertNotNull(mergedProjectManagerRole.getEmployees());
		assertEquals(1, mergedProjectManagerRole.getEmployees().size());

		Employee mergedEa = entityManager.find(Employee.class, ea.getId());
		assertNotNull(mergedEa.getRoles());
		assertEquals(0, mergedEa.getRoles().size());

		Employee mergedEb = entityManager.find(Employee.class, eb.getId());
		assertNotNull(mergedEb.getRoles());
		assertEquals(2, mergedEb.getRoles().size());

	}

	@Test
	public void manyToManyBidirectionalTransientFatherDetachedChildrenBuildTest() {

		// Transient Father
		Role developerRole = new Role();
		developerRole.setName("DEVELOPER_C");

		Employee ea = new Employee();
		ea.setCardId("cardIdE");
		ea.setName("nameE");
		ea.setSurname("surnameE");

		Employee eb = new Employee();
		eb.setCardId("cardIdF");
		eb.setName("nameF");
		eb.setSurname("surnameF");

		entityManager.getTransaction().begin();

		entityManager.persist(ea);
		entityManager.persist(eb);

		entityManager.getTransaction().commit();
		entityManager.clear();

		// detached roles updates
		developerRole.addEmployee(ea);
		developerRole.addEmployee(eb);

		// re-attach and update roles
		entityManager.getTransaction().begin();
		Role mergedDeveloperRole = entityManager.merge(developerRole);
		Set<Employee> employees = developerRole.getEmployees().stream().map(e -> {
			if (entityManager.contains(e)) {
				return e;
			}
			if (e.getId() != null) {
				return entityManager.merge(e);
			}
			return e;
		}).collect(Collectors.toSet());
		mergedDeveloperRole.setEmployees(employees);

		entityManager.getTransaction().commit();
		entityManager.clear();

		// verify data integrity

		mergedDeveloperRole = entityManager.find(Role.class, mergedDeveloperRole.getId());
		assertNotNull(mergedDeveloperRole.getEmployees());
		assertEquals(2, mergedDeveloperRole.getEmployees().size());

		Employee mergedEa = entityManager.find(Employee.class, ea.getId());
		assertNotNull(mergedEa.getRoles());
		assertEquals(1, mergedEa.getRoles().size());

		Employee mergedEb = entityManager.find(Employee.class, eb.getId());
		assertNotNull(mergedEb.getRoles());
		assertEquals(1, mergedEb.getRoles().size());

	}

	@Test
	public void manyToManyBidirectionalDetachedFatherTransientChildrenBuildTest() {

		// Transient Father
		Role developerRole = new Role();
		developerRole.setName("DEVELOPER_D");

		Employee ea = new Employee();
		ea.setCardId("cardIdG");
		ea.setName("nameG");
		ea.setSurname("surnameG");

		Employee eb = new Employee();
		eb.setCardId("cardIdH");
		eb.setName("nameH");
		eb.setSurname("surnameH");

		entityManager.getTransaction().begin();

		entityManager.persist(developerRole);

		entityManager.getTransaction().commit();
		entityManager.clear();

		// transient roles updates
		developerRole.addEmployee(ea);
		developerRole.addEmployee(eb);

		entityManager.getTransaction().begin();

		Role mergedDeveloperRole = entityManager.merge(developerRole);
		Set<Employee> employees = developerRole.getEmployees().stream().map(e -> {
			if (entityManager.contains(e)) {
				return e;
			}
			if (e.getId() != null) {
				return entityManager.merge(e);
			}
			return e;
		}).collect(Collectors.toSet());
		mergedDeveloperRole.setEmployees(employees);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// verify data integrity
		mergedDeveloperRole = entityManager.find(Role.class, mergedDeveloperRole.getId());
		assertNotNull(mergedDeveloperRole.getEmployees());
		assertEquals(2, mergedDeveloperRole.getEmployees().size());

		Employee mergedEa = entityManager.find(Employee.class, ea.getId());
		assertNotNull(mergedEa.getRoles());
		assertEquals(1, mergedEa.getRoles().size());

		Employee mergedEb = entityManager.find(Employee.class, eb.getId());
		assertNotNull(mergedEb.getRoles());
		assertEquals(1, mergedEb.getRoles().size());

	}

	@Test
	public void manyToManyBidirectionalDetachedFatherMixedChildrenBuildTest() {

		// Transient Father
		Role developerRole = new Role();
		developerRole.setName("DEVELOPER_E");

		Employee ea = new Employee();
		ea.setCardId("cardIdI");
		ea.setName("nameI");
		ea.setSurname("surnameI");

		Employee eb = new Employee();
		eb.setCardId("cardIdL");
		eb.setName("nameL");
		eb.setSurname("surnameL");

		entityManager.getTransaction().begin();

		entityManager.persist(developerRole);
		entityManager.persist(ea);

		entityManager.getTransaction().commit();
		entityManager.clear();

		// transient roles update
		developerRole.addEmployee(eb);
		// detached roles update
		developerRole.addEmployee(ea);

		entityManager.getTransaction().begin();

		Role mergedDeveloperRole = entityManager.merge(developerRole);
		Set<Employee> employees = developerRole.getEmployees().stream().map(e -> {
			if (entityManager.contains(e)) {
				return e;
			}
			if (e.getId() != null) {
				return entityManager.merge(e);
			}
			return e;
		}).collect(Collectors.toSet());
		mergedDeveloperRole.setEmployees(employees);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// verify data integrity
		mergedDeveloperRole = entityManager.find(Role.class, mergedDeveloperRole.getId());
		assertNotNull(mergedDeveloperRole.getEmployees());
		assertEquals(2, mergedDeveloperRole.getEmployees().size());

		Employee mergedEa = entityManager.find(Employee.class, ea.getId());
		assertNotNull(mergedEa.getRoles());
		assertEquals(1, mergedEa.getRoles().size());

		Employee mergedEb = entityManager.find(Employee.class, eb.getId());
		assertNotNull(mergedEb.getRoles());
		assertEquals(1, mergedEb.getRoles().size());

	}

	@Test
	public void manyToManyBidirectionalTransientFatherMixedChildrenBuildTest() {

		// Transient Father
		Role developerRole = new Role();
		developerRole.setName("DEVELOPER_F");

		Employee ea = new Employee();
		ea.setCardId("cardIdM");
		ea.setName("nameM");
		ea.setSurname("surnameM");

		Employee eb = new Employee();
		eb.setCardId("cardIdN");
		eb.setName("nameN");
		eb.setSurname("surnameN");

		entityManager.getTransaction().begin();

		entityManager.persist(ea);

		entityManager.getTransaction().commit();
		entityManager.clear();

		// transient roles update
		developerRole.addEmployee(eb);
		// detached roles update
		developerRole.addEmployee(ea);

		entityManager.getTransaction().begin();

		Role mergedDeveloperRole = entityManager.merge(developerRole);
		Set<Employee> employees = developerRole.getEmployees().stream().map(e -> {
			if (entityManager.contains(e)) {
				return e;
			}
			if (e.getId() != null) {
				return entityManager.merge(e);
			}
			return e;
		}).collect(Collectors.toSet());
		mergedDeveloperRole.setEmployees(employees);
		entityManager.getTransaction().commit();
		entityManager.clear();

		// verify data integrity
		mergedDeveloperRole = entityManager.find(Role.class, mergedDeveloperRole.getId());
		assertNotNull(mergedDeveloperRole.getEmployees());
		assertEquals(2, mergedDeveloperRole.getEmployees().size());

		Employee mergedEa = entityManager.find(Employee.class, ea.getId());
		assertNotNull(mergedEa.getRoles());
		assertEquals(1, mergedEa.getRoles().size());

		Employee mergedEb = entityManager.find(Employee.class, eb.getId());
		assertNotNull(mergedEb.getRoles());
		assertEquals(1, mergedEb.getRoles().size());

	}

}
