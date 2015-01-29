/*
	Author: Nibras A. Reeza (cb004641)
	EMail: nibras.me@facebook.com
	Last Modified: 25/05/2012
	
	This file is part of the controller component of Student Management System
	done as part of OOEDP at APIIT, Sri Lanka.
	
	UI delegate of the application. Takes in a view model(if applicable) from
	the Controller and creates and renders relavant interfaces. Also, passes an
	instance of the InputHandler since singleton pattern was not utilized.
 */
 
package apiit.nibras.studentms.controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import apiit.nibras.studentms.controller.models.FrmAddMarksViewModel;
import apiit.nibras.studentms.controller.models.FrmLecturerViewModel;
import apiit.nibras.studentms.controller.models.FrmLoginViewModel;
import apiit.nibras.studentms.controller.models.FrmStudentViewModel;
import apiit.nibras.studentms.controller.models.FrmStudentsViewModel;
import apiit.nibras.studentms.view.frames.FrmAbout;
import apiit.nibras.studentms.view.frames.FrmAddMarks;
import apiit.nibras.studentms.view.frames.FrmLecturer;
import apiit.nibras.studentms.view.frames.FrmLogin;
import apiit.nibras.studentms.view.frames.FrmMain;
import apiit.nibras.studentms.view.frames.FrmStudent;
import apiit.nibras.studentms.view.frames.FrmStudents;
import apiit.nibras.studentms.view.frames.FrmTheme;

public class UIController {
	private InputHandler inputHandler;
	private JFrame ApplicationWindow;

	public UIController(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	public void showLogin(FrmLoginViewModel frmLoginViewModel) {
		@SuppressWarnings("unused")
		FrmLogin frame = new FrmLogin(this.inputHandler, frmLoginViewModel,
				this.ApplicationWindow);
	}

	public void showMainWindow() {
		this.ApplicationWindow = new FrmMain(this.inputHandler);
	}

	public void showAddStudentRecord(FrmStudentViewModel viewModel) {
		FrmStudent frmStudent = new FrmStudent("create", this.inputHandler,
				viewModel);
		this.ApplicationWindow.add(frmStudent);
	}

	public void showViewStudent(FrmStudentViewModel viewModel) {
		FrmStudent frmViewStudent = new FrmStudent("view", this.inputHandler,
				viewModel);
		frmViewStudent.setEnabled(false);
		this.ApplicationWindow.add(frmViewStudent);
		frmViewStudent.present();
	}

	public void showEditStudent(FrmStudentViewModel viewModel) {
		FrmStudent frmEditStudent = new FrmStudent("update", this.inputHandler,
				viewModel);
		this.ApplicationWindow.add(frmEditStudent);
		frmEditStudent.present();
	}

	public void showAddMarks(FrmAddMarksViewModel viewModel) {
		FrmAddMarks frame = new FrmAddMarks(this.inputHandler, viewModel);
		this.ApplicationWindow.add(frame);
	
	}

	public void showViewAllStudents(FrmStudentsViewModel viewModel) {
		// http://stackoverflow.com/questions/9438035/maximizing-jinternalframe-in-java
		FrmStudents frame = new FrmStudents(this.inputHandler, viewModel);
		this.ApplicationWindow.add(frame);
	}

	public void showAddLecturerRecord(FrmLecturerViewModel viewModel) {
		FrmLecturer frmLecturer = new FrmLecturer("create", this.inputHandler,
				viewModel);
		this.ApplicationWindow.add(frmLecturer);
	}

	public void showViewLecturer(FrmLecturerViewModel viewModel) {
		FrmLecturer frmViewLecturer = new FrmLecturer("view",
				this.inputHandler, viewModel);
		frmViewLecturer.setEnabled(false);
		this.ApplicationWindow.add(frmViewLecturer);
		frmViewLecturer.present();
	}

	public void showEditLecturer(FrmLecturerViewModel viewModel) {
		FrmLecturer frmEditLecturer = new FrmLecturer("update",
				this.inputHandler, viewModel);
		this.ApplicationWindow.add(frmEditLecturer);
		frmEditLecturer.present();
	}

	public String getInput(String title, String message) {
		return JOptionPane.showInputDialog(this.ApplicationWindow, message,
				title, JOptionPane.QUESTION_MESSAGE);
	}

	public void showUnpriviledged() {
		JOptionPane
				.showMessageDialog(
						this.ApplicationWindow,
						"You do not have permission to carry out the requested actions!",
						"Permission Denied!", JOptionPane.ERROR_MESSAGE);
	}

	public void showError(String message) {
		JOptionPane.showMessageDialog(this.ApplicationWindow, message,
				"Error!", JOptionPane.ERROR_MESSAGE);
	}

	public void showErrors(List<String> errors) {
		JOptionPane.showMessageDialog(this.ApplicationWindow, errors.toArray(),
				"Invalid Input!", JOptionPane.ERROR_MESSAGE);
	}

	public boolean confirm(String message) {
		int input = JOptionPane.showConfirmDialog(this.ApplicationWindow,
				message, "Are you sure?", JOptionPane.YES_NO_OPTION);
		return input == JOptionPane.YES_OPTION;
	}

	public void showConfirmation(String message) {
		JOptionPane.showMessageDialog(this.ApplicationWindow, message,
				"Success!", JOptionPane.INFORMATION_MESSAGE);
	}

	public void closeInternalFrames() {
		((FrmMain) this.ApplicationWindow).closeFrames();
	}

	public void showThemeManager() {
		FrmTheme frame = new FrmTheme(this.ApplicationWindow);
		this.ApplicationWindow.add(frame);
	}

	public void showAbout() {
		this.ApplicationWindow.add(new FrmAbout());

	}
}
