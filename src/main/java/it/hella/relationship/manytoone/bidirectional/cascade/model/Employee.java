package it.hella.relationship.manytoone.bidirectional.cascade.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * <p>
 * The Class Employee.
 * </p>
 *
 * Implements the one part of a many-to-one bidirectional association.
 * 
 * @see #OneToManyBidirectionalTests
 */
@Entity(name = "EMPLOYEE_CASCADE")
public class Employee implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8180506443708481408L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "EMPLOYEE_ID")
	private Long id;

	/** The name. */
	@Column(name = "NAME", nullable = false)
	private String name;

	/** The surname. */
	@Column(name = "SURNAME", nullable = false)
	private String surname;

	/** The card id. */
	@Column(name = "CARD_ID", nullable = false, unique = true)
	private String cardId;

	/** The project. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID")
	private Project project;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname
	 *            the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the card id.
	 *
	 * @return the card id
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * Sets the card id.
	 *
	 * @param cardId
	 *            the new card id
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project
	 *            the new project
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		return true;
	}

}
