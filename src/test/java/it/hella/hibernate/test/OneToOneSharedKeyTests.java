package it.hella.hibernate.test;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hibernate.id.IdentifierGenerationException;
import org.junit.Test;

import it.hella.relationship.onetoone.sharedkey.model.Address;
import it.hella.relationship.onetoone.sharedkey.model.User;

public class OneToOneSharedKeyTests extends BaseTests {

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

		Address address = entityManager.createQuery("select a from Address a where a.city =:city", Address.class)
				.setParameter("city", "CityU").getSingleResult();
		assertNotNull(address.getUser());
		assertEquals(u.getId(), address.getId());
		assertEquals(u.getId(), address.getUser().getId());

		User user = entityManager.createQuery("select u from User u where u.name =:name", User.class)
				.setParameter("name", "nameU").getSingleResult();
		assertNotNull(user.getShippingAddress());
		assertEquals(a.getId(), user.getId());
		assertEquals(a.getId(), user.getShippingAddress().getId());

	}

	@Test
	public void persistAddressTest() {

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

		Address address = entityManager.createQuery("select a from Address a where a.city =:city", Address.class)
				.setParameter("city", "CityA").getSingleResult();
		assertNotNull(address.getUser());
		assertEquals(u.getId(), address.getId());
		assertEquals(u.getId(), address.getUser().getId());

		User user = entityManager.createQuery("select u from User u where u.name =:name", User.class)
				.setParameter("name", "nameA").getSingleResult();
		assertNotNull(user.getShippingAddress());
		assertEquals(a.getId(), user.getId());
		assertEquals(a.getId(), user.getShippingAddress().getId());

	}

	@Test
	public void persistUserWithoutAddressTest() {

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

		thrown.expectCause(isA(IdentifierGenerationException.class));

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
