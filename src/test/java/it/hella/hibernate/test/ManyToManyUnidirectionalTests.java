package it.hella.hibernate.test;

import org.junit.Test;

import it.hella.relationship.manytomany.unidirectional.model.Employee;
import it.hella.relationship.manytomany.unidirectional.model.Role;

public class ManyToManyUnidirectionalTests extends BaseTests {

	@Test
	public void manyToManyPersistRoleSideTest() {

		Role developerRole = new Role();
		developerRole.setName("DEVELOPER");

		Role projectManagerRole = new Role();
		projectManagerRole.setName("PROJECT_MANAGER");

		entityManager.getTransaction().begin();
		entityManager.persist(developerRole);
		entityManager.persist(projectManagerRole);
		entityManager.getTransaction().commit();

		Employee ea = new Employee();
		ea.setCardId("cardIdA");
		ea.setName("nameA");
		ea.setSurname("surnameA");

		Employee eb = new Employee();
		eb.setCardId("cardIdB");
		eb.setName("nameB");
		eb.setSurname("surnameB");

		developerRole.getEmployees().add(ea);
		developerRole.getEmployees().add(eb);

		entityManager.getTransaction().begin();
		entityManager.merge(developerRole);
		eb = entityManager.createQuery("select e from EMPLOYEE_MTM_UNI e where e.cardId =:cardId", Employee.class)
				.setParameter("cardId", "cardIdB").getSingleResult();
		projectManagerRole.getEmployees().add(eb);
		entityManager.merge(projectManagerRole);
		entityManager.getTransaction().commit();

	}

}
