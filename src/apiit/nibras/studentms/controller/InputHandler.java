/*
	Author: Nibras A. Reeza (cb004641)
	EMail: nibras.me@facebook.com
	Last Modified: 25/05/2012
	
	This file is part of the controller component of Student Management System
	done as part of OOEDP at APIIT, Sri Lanka.
	
	Receives user calls from the interface and delegates them to the controller.
	Was inserted to prevent confusion when interfaces intend to access the 
	controller. This class could be useful if the application may need more than
	one controller essentially reducing the dependency of "view" classes on the
	code and implementation of the controller(s).
 */
 
 package apiit.nibras.studentms.controller;

import apiit.nibras.studentms.controller.models.FrmAddMarksViewModel;
import apiit.nibras.studentms.controller.models.FrmLoginViewModel;
import apiit.nibras.studentms.controller.models.FrmStudentsViewModel;
import apiit.nibras.studentms.controller.models.ViewModel;
import apiit.nibras.studentms.view.frames.ExtendedFrame;
import apiit.nibras.studentms.view.frames.FrmAddMarks;
import apiit.nibras.studentms.view.frames.FrmLogin;
import apiit.nibras.studentms.view.frames.FrmStudents;

public class InputHandler {

	private Controller controller;

	public InputHandler(Controller controller) {
		this.controller = controller;
	}

	public void add(ExtendedFrame frame, ViewModel viewModel) {
		this.controller.add(frame, viewModel);
	}

	public void request(Requests request) {
		this.controller.handleRequest(request);
	}

	public void update(ExtendedFrame frame, ViewModel viewModel) {
		this.controller.update(frame, viewModel);
	}

	public void remove(ExtendedFrame frame, ViewModel viewModel) {
		this.controller.remove(frame, viewModel);

	}

	public void login(FrmLogin frmLogin, FrmLoginViewModel viewModel) {
		this.controller.login(frmLogin, viewModel);
		frmLogin.dispose();
	}

	public void view(FrmStudents frmStudents, FrmStudentsViewModel viewModel) {
		this.controller.view(frmStudents, viewModel);
	}

	public void submit(FrmAddMarks frmAddMarks, FrmAddMarksViewModel viewModel) {
		frmAddMarks.updateModel();
		frmAddMarks.dispose();
	}
}
