/*
	Author: Nibras A. Reeza (cb004641)
	EMail: nibras.me@facebook.com
	Last Modified: 25/05/2012
	
	This file is part of the controller component of Student Management System
	done as part of OOEDP at APIIT, Sri Lanka.
	
	The Repository<E> class defined in this file acts as a wrapper to the data
	store that is to be used to store objects. This classes is introduced to make
	the other classes independent of the data store implementations making any
	future transitions to a Map<E> or a database based system seamless.
 */

package apiit.nibras.studentms.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Repository<E extends Comparable<? super E>> {
	ArrayList<E> list;
	boolean sorted;

	Comparator<? super E> comparator;

	public Repository() {
		this.list = new ArrayList<E>();
		this.resetSorted();
	}

	public Repository(Iterable<E> elements) {
		for (E e : elements)
			this.add(e);
	}

	public void add(E e) {
		this.list.add(e);
		this.resetSorted();
	}

	public E find(E key, Comparator<? super E> comparator) {
		int i = this.getIndex(key, comparator);
		return i == -1 ? null : this.list.get(i);
	}

	public boolean update(E key, Comparator<? super E> comparator, E newObject) {
		int i = this.getIndex(key, comparator);
		if (i == -1)
			return false;

		boolean code = this.list.set(i, newObject) != null;
		this.resetSorted();
		return code;
	}

	public boolean remove(E key, Comparator<? super E> comparator) {
		int i = this.getIndex(key, comparator);
		if (i == -1)
			return false;
		boolean code = this.list.remove(i) != null;
		this.resetSorted();
		return code;
	}

	public E find(E key) {
		int i = this.getIndex(key, this.comparator);
		if (i == -1)
			return null;
		return this.list.get(i);
	}

	public boolean update(E key, E newObject) {
		boolean code = this.list.set(this.getIndex(key), newObject) != null;
		this.resetSorted();
		return code;
	}

	public boolean remove(E key) {
		int i = this.getIndex(key, this.comparator);
		if (i == -1)
			return false;
		boolean code = this.list.remove(i) != null;
		this.resetSorted();
		return code;
	}

	@SuppressWarnings("unchecked")
	public List<E> getAll() {
		return (List<E>) this.list.clone();
	}

	private int getIndex(E key, Comparator<? super E> comparator) {
		if (this.comparator != comparator || !this.sorted) {
			Collections.sort(this.list, comparator);
			this.comparator = comparator;
			this.sorted = true;
		}
		return Collections.binarySearch(this.list, key, comparator);
	}

	private int getIndex(E key) {
		if (!this.sorted || this.comparator != null) {
			Collections.sort(this.list);
			this.comparator = null;
			this.sorted = true;
		}
		return Collections.binarySearch(this.list, key);
	}

	private void resetSorted() {
		this.sorted = false;
		this.comparator = null;
	}
}