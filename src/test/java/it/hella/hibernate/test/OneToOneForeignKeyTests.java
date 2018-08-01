package it.hella.hibernate.test;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hibernate.PropertyValueException;
import org.junit.Test;

import it.hella.relationship.onetoone.foreignkey.model.Address;
import it.hella.relationship.onetoone.foreignkey.model.User;

public class OneToOneForeignKeyTests extends BaseTests {

	@Test
	public void persistUserTest() {

		User u = new User();
		u.setName("nameU");
		u.setSurname("surnameU");

		Address a = new Address();
		a.setCity("cityU");
		a.setStreet("streetU");
		a.setZipcode("zipcodeU");

		u.setShippingAddress(a);
		a.setUser(u);
		entityManager.getTransaction().begin();
		entityManager.persist(u);
		entityManager.getTransaction().commit();
		entityManager.detach(u);

		Address address = entityManager.createQuery("select a from Address_Fk a where a.city =:city", Address.class)
				.setParameter("city", "CityU").getSingleResult();
		assertNotNull(address.getUser());
		assertEquals(u.getId(), address.getUser().getId());

		User user = entityManager.createQuery("select u from User_Fk u where u.name =:name", User.class)
				.setParameter("name", "nameU").getSingleResult();
		assertNotNull(user.getShippingAddress());
		assertEquals(a.getId(), user.getShippingAddress().getId());

	}

	/**
	 * <p>
	 * This test throws an IllegalStateException as address is not the owning
	 * entity.
	 * </p>
	 * 
	 * <b>Anyway, if you set the cascade type to PERSIST the test will fail
	 * (i.e. saving successfully the address)</b>
	 * 
	 */
	@Test
	public void persistAddressTest() {

		thrown.expectCause(isA(IllegalStateException.class));

		User u = new User();
		u.setName("nameA");
		u.setSurname("surnameA");

		Address a = new Address();
		a.setCity("cityA");
		a.setStreet("streetA");
		a.setZipcode("zipcodeA");

		u.setShippingAddress(a);
		a.setUser(u);
		entityManager.getTransaction().begin();
		entityManager.persist(a);
		entityManager.getTransaction().commit();
		entityManager.detach(a);

	}

	@Test
	public void persistUserWithoutAddressTest() {

		thrown.expectCause(isA(PropertyValueException.class));

		User u = new User();
		u.setName("nameWA");
		u.setSurname("surnameWA");
		entityManager.getTransaction().begin();
		entityManager.persist(u);
		entityManager.getTransaction().commit();
		entityManager.detach(u);

	}

	@Test
	public void persistAddressWithoutUserTest() {

		thrown.expectCause(isA(PropertyValueException.class));

		Address a = new Address();
		a.setCity("cityWU");
		a.setStreet("streetWU");
		a.setZipcode("zipcodeWU");
		entityManager.getTransaction().begin();
		entityManager.persist(a);
		entityManager.getTransaction().commit();
		entityManager.detach(a);

	}

}
