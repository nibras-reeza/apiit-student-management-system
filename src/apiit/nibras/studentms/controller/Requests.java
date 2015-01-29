/*
	Author: Nibras A. Reeza (cb004641)
	EMail: nibras.me@facebook.com
	Last Modified: 25/05/2012
	
	This file is part of the controller component of Student Management System
	done as part of OOEDP at APIIT, Sri Lanka.
	
	Enum. Provides a list of "Requests" that a view may request from the controller.
	Simplifies the logic of "view". Essentially forming a contract of what may be
	requested from the application/controller and providing uniformity and ease
	in making the InputHandler.request() call.
 */
 
package apiit.nibras.studentms.controller;

public enum Requests {
	NEW_STUDENT, VIEW_STUDENT, VIEW_STUDENTS, EDIT_STUDENT, DEL_STUDENT,

	NEW_LECTURER, VIEW_LECTURER, EDIT_LECTURER, DEL_LECTURER,

	ADD_MARK, LOGOUT, LOGIN, HELP, ABOUT, EDIT_PW, EXIT, THEME, UML,

}