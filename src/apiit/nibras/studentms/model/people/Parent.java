/*
	Author: Nibras A. Reeza (cb004641)
	EMail: nibras.me@facebook.com
	Last Modified: 25/05/2012
	
	This file is part of the model component of Student Management System
	done as part of OOEDP at APIIT, Sri Lanka.
	
	Represents a parent that of a student. Considered part of the system.
	However, it is not considered directly relavant. No special/additional
	attributes or behaviors are defined.
 */
 
 package apiit.nibras.studentms.model.people;

import apiit.nibras.studentms.model.types.ContactDetails;
import apiit.nibras.studentms.model.types.PersonalDetails;

public class Parent extends Person {

	public Parent(PersonalDetails personalDetails, ContactDetails contactDetails) {
		super(personalDetails, contactDetails);
	}

	public Parent(PersonalDetails personalDetails) {
		super(personalDetails);
	}

}
