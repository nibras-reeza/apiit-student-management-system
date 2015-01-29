/*
	Author: Nibras A. Reeza (cb004641)
	EMail: nibras.me@facebook.com
	Last Modified: 25/05/2012
	
	This file is part of the model component of Student Management System
	done as part of OOEDP at APIIT, Sri Lanka.
	
	Represents a person directly relevant to the system. Centralizes the
	attributes shared by Student and Lecturer classes.
 */
 
 package apiit.nibras.studentms.model.people;

import java.util.ArrayList;
import java.util.List;

import apiit.nibras.studentms.model.types.ContactDetails;
import apiit.nibras.studentms.model.types.EducationHistory;
import apiit.nibras.studentms.model.types.PersonalDetails;
import apiit.nibras.studentms.model.types.Program;
import apiit.nibras.studentms.model.types.Subject;

public abstract class Member extends Person implements Comparable<Member> {

	protected String password = "123456";
	protected String id;
	protected Program program;
	protected List<Subject> subjects;
	protected List<EducationHistory> academicHistory;

	public Member() {
		super();
	}

	public Member(PersonalDetails personalDetails, ContactDetails contactDetails) {
		super(personalDetails, contactDetails);
		this.subjects = new ArrayList<Subject>();
		this.academicHistory = new ArrayList<EducationHistory>();
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return this.id;
	}

	public Program getProgram() {
		return this.program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public void addSubject(Subject subject) {
		if (this instanceof Student)
			subject.addStudent((Student) this);
		this.subjects.add(subject);
	}

	public void addAcademicHistory(EducationHistory educationHistory) {
		this.academicHistory.add(educationHistory);
	}

	public boolean removeSubject(Subject subject) {
		if (this instanceof Student)
			subject.removeStudent((Student) this);
		return this.subjects.remove(subject);
	}

	public boolean removeAcademicHistory(EducationHistory educationHistory) {
		return this.academicHistory.remove(educationHistory);
	}

	public List<Subject> getSubjects() {
		return this.subjects;
	}

	public List<EducationHistory> getAcademicHistory() {
		return this.academicHistory;
	}

	public int compareTo(Member member) {
		return this.getId().compareTo(member.getId());
	}

}