/*
	Author: Nibras A. Reeza (cb004641)
	EMail: nibras.me@facebook.com
	Last Modified: 25/05/2012
	
	This file is part of the model component of Student Management System
	done as part of OOEDP at APIIT, Sri Lanka.
	
	Represents a person. This acts as the parent class for other classes such
	as Student, Lecturer, System Administrator and Parent. Reduces code
	redundancy and improvises a OOP heierarchy. Abstract class.
 */
 
 package apiit.nibras.studentms.model.people;

import apiit.nibras.studentms.model.types.ContactDetails;
import apiit.nibras.studentms.model.types.PersonalDetails;

public abstract class Person {
	protected PersonalDetails personalDetails;
	protected ContactDetails contactDetails;

	public Person() {
		super();
	}

	public Person(PersonalDetails personalDetails) {
		super();
		this.personalDetails = personalDetails;
	}

	public Person(PersonalDetails personalDetails, ContactDetails contactDetails) {
		super();
		this.personalDetails = personalDetails;
		this.contactDetails = contactDetails;
	}

	public PersonalDetails getPersonalDetails() {
		return this.personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public ContactDetails getContactDetails() {
		return this.contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String toString() {
		return this.personalDetails.toString() + "\n"
				+ this.contactDetails.toString();
	}

}
