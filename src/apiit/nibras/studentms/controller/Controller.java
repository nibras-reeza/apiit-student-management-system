/*
	Author: Nibras A. Reeza (cb004641)
	EMail: nibras.me@facebook.com
	Last Modified: 25/05/2012
	
	This file is part of the controller component of Student Management System
	done as part of OOEDP at APIIT, Sri Lanka.
	
	This is the main class of the application. It receives user input from the
	InputHandler and processes it displaying data and manipulating the data
	store as required. Note that this class contains all the "dirty" logic. It 
	delegates logic to instantiate and display user interfaces to the UIController.
	This class acts as the main controller that brings together every other
	class in the project.
 */

package apiit.nibras.studentms.controller;

import java.awt.Container;
import java.awt.Desktop;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import apiit.nibras.studentms.controller.AuthenticationManager.Permission;
import apiit.nibras.studentms.controller.models.FrmAddMarksViewModel;
import apiit.nibras.studentms.controller.models.FrmLecturerViewModel;
import apiit.nibras.studentms.controller.models.FrmLoginViewModel;
import apiit.nibras.studentms.controller.models.FrmStudentViewModel;
import apiit.nibras.studentms.controller.models.FrmStudentsViewModel;
import apiit.nibras.studentms.controller.models.ViewModel;
import apiit.nibras.studentms.model.people.Lecturer;
import apiit.nibras.studentms.model.people.Member;
import apiit.nibras.studentms.model.people.Student;
import apiit.nibras.studentms.model.people.SystemAdministrator;
import apiit.nibras.studentms.model.types.ContactDetails;
import apiit.nibras.studentms.model.types.PersonalDetails;
import apiit.nibras.studentms.model.types.Program;
import apiit.nibras.studentms.model.types.Stream;
import apiit.nibras.studentms.model.types.Subject;
import apiit.nibras.studentms.view.frames.ExtendedFrame;
import apiit.nibras.studentms.view.frames.FrmLecturer;
import apiit.nibras.studentms.view.frames.FrmLogin;
import apiit.nibras.studentms.view.frames.FrmStudent;
import apiit.nibras.studentms.view.frames.FrmStudents;
import au.com.bytecode.opencsv.CSVReader;

public class Controller {

	public static void main(String[] args) {

		Controller controller = new Controller();
		controller.initialize();
		controller.loadSubjects();
		controller.loadApp();

	}

	private AuthenticationManager authManager;
	private InputHandler inputHandler;
	private UIController UIcontroller;

	private DataHandler dataHandler;

	public void handleRequest(Requests request) {
		switch (request) {
		case ABOUT:
			this.about();
			break;
		case ADD_MARK:
			this.addMarks();
			break;
		case DEL_LECTURER:
			this.delLecturer();
			break;
		case DEL_STUDENT:
			this.delStudent();
			break;
		case EDIT_LECTURER:
			this.editLecturer();
			break;
		case EDIT_PW:
			this.editPw();
			break;
		case EDIT_STUDENT:
			this.editStudent();
			break;
		case EXIT:
			this.exit();
			break;
		case HELP:
			this.help();
			break;
		case LOGIN:
			this.login();
			break;
		case LOGOUT:
			this.logout();
			break;
		case NEW_LECTURER:
			this.newLecturer();
			break;
		case NEW_STUDENT:
			this.newStudent();
			break;
		case THEME:
			this.theme();
			break;
		case UML:
			this.uml();
			break;
		case VIEW_LECTURER:
			this.viewLecturer();
			break;
		case VIEW_STUDENT:
			this.viewStudent();
			break;
		case VIEW_STUDENTS:
			this.viewStudents();
			break;
		default:
			break;
	
		}
	
	}

	public void initialize() {
		this.authManager = new AuthenticationManager();
		this.dataHandler = new DataHandler();
		this.inputHandler = new InputHandler(this);
		this.UIcontroller = new UIController(this.inputHandler);
	
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	
		this.loadConfig();
	
	}

	public void loadApp() {
		this.UIcontroller.showMainWindow();
		this.UIcontroller.showLogin(new FrmLoginViewModel());
	}

	private SystemAdministrator admin;

	public void login(FrmLogin frmLogin, FrmLoginViewModel viewModel) {
	
		frmLogin.updateModel();
	
		Member m;
	
		if (viewModel.getUserName().equals("admin"))
			m = this.admin;
		else {
	
			m = new Lecturer(viewModel.getUserName());
			m = this.dataHandler.find((Lecturer) m);
		}
	
		if (m == null)
			this.UIcontroller.showError("Invalid User Name!");
		else if (this.authManager.authenticate(m, viewModel.getPassword())) {
			this.UIcontroller.showConfirmation("Logged in Successfully!");
			frmLogin.dispose();
		} else {
			this.UIcontroller.showError("Invalid Password!");
			// this.handleRequest(Requests.LOGIN);
		}
	
	}

	public void add(Container c, ViewModel viewModel) {
		if (this.authManager.hasPermission(Permission.EDIT)) {
			List<String> errors;
			errors = viewModel.validate();
			if (!(errors == null))
				this.UIcontroller.showErrors(errors);
			else {
				Object o = viewModel.toObject();
	
				if (o instanceof Student) {
					this.dataHandler.add((Student) o);
					this.UIcontroller.showConfirmation(((Member) o).getId()
							+ "was added successfully!");
				} else if (o instanceof Lecturer) {
					this.dataHandler.add((Lecturer) o);
					this.UIcontroller.showConfirmation(((Member) o).getId()
							+ "was added successfully!");
	
				} else if (o instanceof Subject)
					this.dataHandler.add((Subject) o);
	
				if (c instanceof ExtendedFrame)
					((ExtendedFrame) c).close();
	
			}
	
		} else
			this.UIcontroller.showUnpriviledged();
	}

	public void remove(ExtendedFrame frame, ViewModel viewModel) {
		if (this.authManager.hasPermission(Permission.EDIT)) {
			if (frame instanceof FrmStudent
					&& viewModel instanceof FrmStudentViewModel)
				this.dataHandler.remove(new Student(
						((FrmStudentViewModel) viewModel).getId()));
			if (frame instanceof FrmLecturer
					&& viewModel instanceof FrmLecturerViewModel)
				this.dataHandler.remove(new Lecturer(
						((FrmLecturerViewModel) viewModel).getId()));
		} else
			this.UIcontroller.showUnpriviledged();
	}

	public void update(ExtendedFrame frame, ViewModel viewModel) {
		if (this.authManager.hasPermission(Permission.EDIT)) {
			if (frame instanceof FrmStudent
					&& viewModel instanceof FrmStudentViewModel
					&& ((FrmStudent) frame).getType().equals("view"))
				this.UIcontroller
						.showEditStudent((FrmStudentViewModel) viewModel);
			else if (frame instanceof FrmStudent
					&& viewModel instanceof FrmStudentViewModel
					&& ((FrmStudent) frame).getType().equals("update")) {
	
				((FrmStudentViewModel) viewModel).updateObject();
			}
			if (frame instanceof FrmLecturer
					&& viewModel instanceof FrmLecturerViewModel
					&& ((FrmLecturer) frame).getType().equals("view"))
				this.UIcontroller
						.showEditLecturer((FrmLecturerViewModel) viewModel);
			else if (frame instanceof FrmLecturer
					&& viewModel instanceof FrmLecturerViewModel
					&& ((FrmLecturer) frame).getType().equals("update")) {
	
				((FrmLecturerViewModel) viewModel).updateObject();
			}
			frame.close();
		} else
			this.UIcontroller.showUnpriviledged();
	}

	public void view(FrmStudents frmStudents, FrmStudentsViewModel viewModel) {
	
		String stu_id = viewModel.getId();
		if (this.authManager
				.hasPermission(AuthenticationManager.Permission.VIEW)) {
			Student key = new Student(stu_id);
			Student student = this.dataHandler.find(key);
			if (student != null)
				this.UIcontroller.showViewStudent(new FrmStudentViewModel(
						student, this.dataHandler.getAllSubjects()));
			else
				this.UIcontroller.showUnpriviledged();
	
		}
	}

	private void about() {
		this.UIcontroller.showAbout();
	}

	private void addMarks() {
		if (this.authManager.hasPermission(Permission.MARKS))
			this.UIcontroller.showAddMarks(new FrmAddMarksViewModel(
					(Lecturer) this.authManager.getCurrentUser()));
		else
			this.UIcontroller.showUnpriviledged();
	}

	private void delLecturer() {
		if (this.authManager.hasPermission(Permission.EDIT)) {
			String lect_ID = this.getLecturerID();
			if (lect_ID != null) {
				Lecturer l = this.dataHandler.find(new Lecturer(lect_ID));
				if (l != null) {
					if (this.UIcontroller
							.confirm("Are you sure you want to delete "
									+ l.getId() + "?"))
						this.dataHandler.remove(l);
				} else
					this.UIcontroller.showError("Invalid ID");
			}
		} else
			this.UIcontroller.showUnpriviledged();
	}

	private void delStudent() {
		if (this.authManager.hasPermission(Permission.EDIT)) {
			String stu_ID = this.getStudentID();
			if (stu_ID != null) {
				Student s = this.dataHandler.find(new Student(stu_ID));
				if (s != null) {
					if (this.UIcontroller
							.confirm("Are you sure you want to delete "
									+ s.getId() + "?"))
						this.dataHandler.remove(s);
				} else
					this.UIcontroller.showError("Invalid ID");
			}
		} else
			this.UIcontroller.showUnpriviledged();
	}

	private void editLecturer() {
		if (this.authManager.hasPermission(Permission.EDIT)) {
			String lect_ID = this.getLecturerID();
			if (lect_ID != null) {
				Lecturer l = this.dataHandler.find(new Lecturer(lect_ID));
				if (l != null) {

					this.UIcontroller
							.showEditLecturer(new FrmLecturerViewModel(l,
									this.dataHandler.getAllSubjects()));
				} else
					this.UIcontroller.showError("Invalid ID");
			}
		} else
			this.UIcontroller.showUnpriviledged();
	}

	private void editPw() {
		if (this.authManager.hasPermission(Permission.MARKS)) {
			String newPW = this.UIcontroller.getInput("New Password",
					"Please enter the new password?");
			this.authManager.getCurrentUser().setPassword(newPW);
		} else
			this.UIcontroller.showUnpriviledged();
	}

	private void editStudent() {
		if (this.authManager.hasPermission(Permission.EDIT)) {
			String stud_ID = this.getStudentID();
			if (stud_ID != null) {
				Student s = this.dataHandler.find(new Student(stud_ID));
				if (s != null) {

					this.UIcontroller.showEditStudent(new FrmStudentViewModel(
							s, this.dataHandler.getAllSubjects()));
				} else
					this.UIcontroller.showError("Invalid ID");
			}
		} else
			this.UIcontroller.showUnpriviledged();
	}

	private void exit() {
		if (this.UIcontroller.confirm("Are you sure you want to exit?")) {
			this.UIcontroller.closeInternalFrames();
			System.exit(0);
		}
	}

	private String getLecturerID() {
		return this.UIcontroller.getInput("Lecturer ID",
				"Please enter ID of the lecturer to look up!");
	}

	private String getStudentID() {
		return this.UIcontroller.getInput("Student ID",
				"Please enter ID of the student to look up!");
	}

	private void help() {
		try {
			Desktop.getDesktop().open(new File("help.doc"));
		} catch (IOException e) {
			this.UIcontroller.showError("Help File not found!");
		}
	}

	private void loadConfig() {
		CSVReader config;
		try {
			config = new CSVReader(new FileReader("sms.config"));
			List<String[]> list = config.readAll();
			String[] line = list.get(1);
			PersonalDetails pd = new PersonalDetails(line[0], line[1]);
			line = list.get(2);
			ContactDetails cd = new ContactDetails(line[0], line[1], line[2],
					line[3], line[4]);
			this.admin = new SystemAdministrator(pd, cd);
		} catch (IOException e) {
			this.UIcontroller.showError("Cannot find config file!");
		}

	}

	private void loadSubjects() {
		// Following code adapted from http://opencsv.sourceforge.net/
		try {
			CSVReader csvr = new CSVReader(new FileReader("subjects.csv"), '\t');
			List<String[]> subjects = csvr.readAll();
			for (String[] line : subjects)
				this.dataHandler.add(new Subject(line[0], line[1], Program
						.valueOf(line[2]), Stream.valueOf(line[3])));
			csvr.close();
		} catch (IOException e) {
			this.UIcontroller
					.showError("Cannot find file containing Subject data!");
		}

	}

	private void login() {
		this.UIcontroller.showLogin(new FrmLoginViewModel());
	}

	private void logout() {
		this.UIcontroller.closeInternalFrames();
		this.authManager.logout();
		this.handleRequest(Requests.LOGIN);
	}

	private void newLecturer() {
		if (this.authManager
				.hasPermission(AuthenticationManager.Permission.EDIT))
			this.UIcontroller.showAddLecturerRecord(new FrmLecturerViewModel(
					Lecturer.getNEXT_ID(), this.dataHandler.getAllSubjects()));
		else
			this.UIcontroller.showUnpriviledged();
	}

	private void newStudent() {
		if (this.authManager
				.hasPermission(AuthenticationManager.Permission.EDIT))
			this.UIcontroller.showAddStudentRecord(new FrmStudentViewModel(
					Student.getNEXT_ID(), this.dataHandler.getAllSubjects()));
		else
			this.UIcontroller.showUnpriviledged();
	}

	private void theme() {
		this.UIcontroller.showThemeManager();
	}

	private void uml() {
		try {
			Desktop.getDesktop().open(new File("uml.doc"));
		} catch (IOException e) {
			this.UIcontroller.showError("UML Documentation File not found!");
		}
	}

	private void viewLecturer() {

		if (this.authManager
				.hasPermission(AuthenticationManager.Permission.VIEW)) {
			String lect_id = this.getLecturerID();
			if (lect_id != null) {
				Lecturer key = new Lecturer(lect_id);
				Lecturer lecturer = this.dataHandler.find(key);
				if (lecturer != null) {
					this.UIcontroller
							.showViewLecturer(new FrmLecturerViewModel(
									lecturer, this.dataHandler.getAllSubjects()));
				} else
					this.UIcontroller.showError("Invalid ID!");
			}

		} else
			this.UIcontroller.showUnpriviledged();
	}

	private void viewStudent() {

		if (this.authManager
				.hasPermission(AuthenticationManager.Permission.VIEW)) {
			String stu_id = this.getStudentID();
			if (stu_id != null) {
				Student key = new Student(stu_id);
				Student student = this.dataHandler.find(key);
				if (student != null)
					this.UIcontroller.showViewStudent(new FrmStudentViewModel(
							student, this.dataHandler.getAllSubjects()));
				else
					this.UIcontroller.showError("Invalid ID!");
			}

		} else
			this.UIcontroller.showUnpriviledged();
	}

	private void viewStudents() {
		if (this.authManager
				.hasPermission(AuthenticationManager.Permission.VIEW)) {
			this.UIcontroller.showViewAllStudents(new FrmStudentsViewModel(
					this.dataHandler.getAllStudents()));
		} else
			this.UIcontroller.showUnpriviledged();
	}

}

/*
	References
	Opencsv.sourceforge.net (2011) opencsv - Frequently Asked Questions. [online] 
	Available at: http://opencsv.sourceforge.net/ [Accessed: 7 Jan 2013].
*/