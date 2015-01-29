/*
	Author: Nibras A. Reeza (cb004641)
	EMail: nibras.me@facebook.com
	Last Modified: 25/05/2012
	
	This file is part of the controller component of Student Management System
	done as part of OOEDP at APIIT, Sri Lanka.
	
	This file defines the Authentication Manager of the system. Currently, it
	takes in an instance of a Member class and checks if the password passed is
	valid against the password stored for the member. The default password is
	123456. This class could be adapted as a wrapper to JAAS or OODBMS based 
	authentication system.
 */
 
 package apiit.nibras.studentms.controller;

import apiit.nibras.studentms.model.people.Lecturer;
import apiit.nibras.studentms.model.people.Member;
import apiit.nibras.studentms.model.people.SystemAdministrator;

public class AuthenticationManager {

	private Member currentUser;
	private boolean authenticated;

	enum Permission {
		EDIT, MARKS, VIEW
	}

	public boolean authenticate(Member member, String password) {
		this.authenticated = member.getPassword().equals(password);
		this.currentUser = member;
		return this.authenticated;
	}

	public void logout() {
		this.currentUser = null;
		this.authenticated = false;
	}

	public boolean hasPermission(Permission permission) {
		boolean hasPermission = false;
		switch (permission) {
		case VIEW:
			hasPermission = true;
			break;
		case MARKS:
			hasPermission = this.currentUser instanceof Lecturer;
			break;
		case EDIT:
			hasPermission = this.currentUser instanceof SystemAdministrator;
			break;

		}
		return this.authenticated && hasPermission;
	}

	public Member getCurrentUser() {
		return this.currentUser;
	}
}
