/*
	Author: Nibras A. Reeza (cb004641)
	EMail: nibras.me@facebook.com
	Last Modified: 25/05/2012
	
	This file is part of the controller component of Student Management System
	done as part of OOEDP at APIIT, Sri Lanka.
	
	This class acts as the data handler of the Student Management System. It
	provides polymorphic add, remove, update and find functions that delegate
	the calls to an instance of the Repository. If the program needs access to
	multiple data resources, this class could be refactored to support all types
	of resources preventing clutter in other classes which access the data. 
	Where search/sorting is required, this class also supports taking in an 
	additional comparator to search/sort objects by a method other than the
	default order implemented by the Comparable<E> interface.
 */
 
 package apiit.nibras.studentms.controller;

import java.util.Comparator;
import java.util.List;

import apiit.nibras.studentms.model.people.Lecturer;
import apiit.nibras.studentms.model.people.Student;
import apiit.nibras.studentms.model.types.Subject;

public class DataHandler {
	private Repository<Student> students;
	private Repository<Lecturer> lecturers;
	private Repository<Subject> subjects;

	public DataHandler() {
		this.students = new Repository<Student>();
		this.lecturers = new Repository<Lecturer>();
		this.subjects = new Repository<Subject>();
	}

	public void add(Student student) {
		this.students.add(student);
	}

	public boolean remove(Student student) {
		return this.students.remove(student);
	}

	public boolean remove(Student key, Comparator<? super Student> comparator) {
		return this.students.remove(key, comparator);
	}

	public boolean update(Student oldStudent, Student newStudent) {
		return this.students.update(oldStudent, newStudent);
	}

	public boolean update(Student key, Comparator<? super Student> comparator,
			Student newStudent) {
		return this.students.update(key, comparator, newStudent);
	}

	public Student find(Student key) {
		return this.students.find(key);
	}

	public Student find(Student key, Comparator<? super Student> comparator) {
		return this.students.find(key, comparator);
	}

	public List<Student> getAllStudents() {
		return this.students.getAll();
	}

	public void add(Lecturer lecturer) {
		this.lecturers.add(lecturer);
	}

	public boolean remove(Lecturer lecturer) {
		return this.lecturers.remove(lecturer);
	}

	public boolean remove(Lecturer key, Comparator<? super Lecturer> comparator) {
		return this.lecturers.remove(key, comparator);
	}

	public boolean update(Lecturer oldLecturer, Lecturer newLecturer) {
		return this.lecturers.update(oldLecturer, newLecturer);
	}

	public boolean update(Lecturer key,
			Comparator<? super Lecturer> comparator, Lecturer newLecturer) {
		return this.lecturers.update(key, comparator, newLecturer);
	}

	public Lecturer find(Lecturer key) {
		return this.lecturers.find(key);
	}

	public Lecturer find(Lecturer key, Comparator<? super Lecturer> comparator) {
		return this.lecturers.find(key, comparator);
	}

	public List<Lecturer> getAllLecturers() {
		return this.lecturers.getAll();
	}

	public void add(Subject subject) {
		this.subjects.add(subject);
	}

	public boolean remove(Subject subject) {
		return this.subjects.remove(subject);
	}

	public boolean remove(Subject key, Comparator<? super Subject> comparator) {
		return this.subjects.remove(key, comparator);
	}

	public boolean update(Subject oldModule, Subject newModule) {
		return this.subjects.update(oldModule, newModule);
	}

	public boolean update(Subject key, Comparator<? super Subject> comparator,
			Subject newModule) {
		return this.subjects.update(key, comparator, newModule);
	}

	public Subject find(Subject key) {
		return this.subjects.find(key);
	}

	public Subject find(Subject key, Comparator<? super Subject> comparator) {
		return this.subjects.find(key, comparator);
	}

	public List<Subject> getAllSubjects() {
		return this.subjects.getAll();
	}

}
