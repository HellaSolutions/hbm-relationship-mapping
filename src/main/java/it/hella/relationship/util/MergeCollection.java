package it.hella.relationship.util;

import java.util.Collection;

import javax.persistence.EntityManager;

public class MergeCollection {

	private MergeCollection() {
	}

	public static final <T> void merge(Collection<T> target, Collection<T> updated, final EntityManager manager) {

		target.clear();
		updated.forEach(u -> {
			T d = manager.merge(u);
			target.add(manager.merge(d));
		});

	}

}
