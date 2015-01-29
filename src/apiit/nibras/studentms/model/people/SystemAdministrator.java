/*
	Author: Nibras A. Reeza (cb004641)
	EMail: nibras.me@facebook.com
	Last Modified: 25/05/2012
	
	This file is part of the model component of Student Management System
	done as part of OOEDP at APIIT, Sri Lanka.
	
	Represents the administrator of the system. Was introduces to 
	differentiates the System Administrator from other people represented
	in the system.
 */
 
 package apiit.nibras.studentms.model.people;

import apiit.nibras.studentms.model.types.ContactDetails;
import apiit.nibras.studentms.model.types.PersonalDetails;

public class SystemAdministrator extends Member {

	public SystemAdministrator(PersonalDetails personalDetails,
			ContactDetails contactDetails) {
		super(personalDetails, contactDetails);
	}

}
