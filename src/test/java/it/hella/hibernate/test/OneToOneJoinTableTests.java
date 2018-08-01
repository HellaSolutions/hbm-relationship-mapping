package it.hella.hibernate.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import it.hella.relationship.onetoone.jointable.model.Address;
import it.hella.relationship.onetoone.jointable.model.User;

public class OneToOneJoinTableTests extends BaseTests {

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
		entityManager.getTransaction().begin();
		entityManager.persist(u);
		entityManager.getTransaction().commit();
		entityManager.detach(u);

		User user = entityManager.createQuery("select u from User_Jt u where u.name =:name", User.class)
				.setParameter("name", "nameU").getSingleResult();
		assertNotNull(user.getShippingAddress());
		assertEquals(a.getId(), user.getShippingAddress().getId());

		Address address = entityManager
				.createQuery("select u.shippingAddress from User_Jt u where u.shippingAddress =:adr", Address.class)
				.setParameter("adr", a).getSingleResult();
		assertEquals(address.getId(), user.getShippingAddress().getId());

		address = entityManager.createQuery("select a from Address_Jt a where a =:adr", Address.class)
				.setParameter("adr", a).getSingleResult();
		assertEquals(address.getId(), user.getShippingAddress().getId());

	}

	// @Test
	// public void persistAddressTest() {
	//
	// User u = new User();
	// u.setName("nameA");
	// u.setSurname("surnameA");
	//
	// Address a = new Address();
	// a.setCity("cityA");
	// a.setStreet("streetA");
	// a.setZipcode("zipcodeA");
	//
	// a.setUser(u);
	// entityManager.getTransaction().begin();
	// entityManager.persist(a);
	// entityManager.getTransaction().commit();
	// entityManager.detach(a);
	//
	// // User user = entityManager.createQuery("select u from User_Jt u where
	// // u.name =:name", User.class)
	// // .setParameter("name", "nameA").getSingleResult();
	// // assertNotNull(user.getShippingAddress());
	// // assertEquals(a.getId(), user.getShippingAddress().getId());
	//
	// Address address = entityManager.createQuery("select a from Address_Jt a
	// where a.city =:city", Address.class)
	// .setParameter("city", "CityA").getSingleResult();
	// assertNotNull(address.getUser());
	// assertEquals(u.getId(), address.getUser().getId());
	//
	// }

}
