package it.hella.relationship.manytoone.unidirectional.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * <p>
 * The Class Project.
 * </p>
 *
 * Implements the many part of a many-to-one unidirectional association.
 * 
 * @see #ManyToOneUnidirectionalTests
 * 
 */
@Entity(name = "PROJECT_MTO_UNI")
public class Project implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6757728647892564923L;

	/** The id. */
	@Id
	@GeneratedValue
	@Column(name = "PROJECT_ID")
	private Long id;

	/** The name. */
	@Column(name = "NAME")
	private String name;

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

}
