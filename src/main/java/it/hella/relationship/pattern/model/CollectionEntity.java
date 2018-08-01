package it.hella.relationship.pattern.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

public class CollectionEntity {

	private Set<ElementEntity> elements = new HashSet<>();
	private Set<ElementEntity> removed = new HashSet<>();
	private Set<ElementEntity> added = new HashSet<>();

	public void addElement(ElementEntity e) {
		if (e == null || added.contains(e)) {
			return;
		}
		removed.remove(e);
		added.add(e);
	}

	public void removeElement(ElementEntity e) {
		if (e == null || removed.contains(e)) {
			return;
		}
		added.remove(e);
		removed.add(e);
	}

	public Set<ElementEntity> getElements() {
		if (!elements.isEmpty()) {
			return Collections.unmodifiableSet(elements);
		}
		return new HashSet<ElementEntity>();
	}

	public void merge(EntityManager em) {

		final CollectionEntity collectionEntity = em.contains(this) ? this : em.merge(this);
		added.forEach(e -> {
			if (!elements.contains(e)) {
				if (!em.contains(e) && e.getId() != null) {
					e = em.merge(e);
				}
				elements.add(e);
				e.setParent(collectionEntity);
			}
		});
		removed.forEach(e -> {
			if (elements.contains(e)) {
				if (!em.contains(e) && e.getId() != null) {
					e = em.merge(e);
				}
				elements.remove(e);
				e.setParent(null);
			}
		});

	}

}
