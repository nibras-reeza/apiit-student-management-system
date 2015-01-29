package apiit.nibras.studentms.view.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import apiit.nibras.studentms.controller.InputHandler;
import apiit.nibras.studentms.controller.Requests;

public class FrmMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2355608030471915939L;
	private JDesktopPane desktopPane;
	private JMenuBar menuBar;
	private JMenu mnAdd;
	private JMenuItem mntmNewStudent;
	private InputHandler inputHandler;
	private JMenu mnFind;
	private JMenu mnView;
	private JMenu mnHelp;
	private JMenuItem mntmNewLecturer;
	private JMenuItem mntmStudent;
	private JMenuItem mntmLecturer;
	private JMenuItem mntmMarks;
	private JMenu mnFile;
	private JMenuItem mntmLogin;
	private JMenuItem mntmLogout;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	private JMenuItem mntmStudents;
	private JSeparator separator;
	private JSeparator separator_1;
	private JMenuItem mntmViewHelp;
	private JSeparator separator_2;
	private JMenuItem mntmChangePassword;
	private JSeparator separator_3;
	private JMenuItem mntmViewUmlDocumentation;
	private JMenu mnUpdate;
	private JMenu mnDelete;
	private JMenuItem mntmStudent_1;
	private JMenuItem mntmLecturer_1;
	private JMenuItem mntmStudent_2;
	private JMenuItem mntmLecturer_2;
	private JSeparator separator_4;
	private JMenuItem mntmTheme;

	/**
	 * Create the frame.
	 */
	public FrmMain(InputHandler inputHandler) {
		this.inputHandler = inputHandler;

		this.setTitle("Student Management System");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(20, 20, 800, 600);

		this.menuBar = new JMenuBar();
		this.setJMenuBar(this.menuBar);

		this.mnFile = new JMenu("File");
		this.menuBar.add(this.mnFile);

		this.mntmLogin = new JMenuItem("Login");
		this.mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmMain.this.handleMntmLoginActionPerformed(arg0);
			}
		});
		this.mnFile.add(this.mntmLogin);

		this.mntmLogout = new JMenuItem("Logout");
		this.mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmMain.this.handleMntmLogoutActionPerformed(arg0);
			}
		});
		this.mnFile.add(this.mntmLogout);

		this.mntmChangePassword = new JMenuItem("New Password");
		this.mntmChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmMain.this.handleMntmChangePasswordActionPerformed(arg0);
			}
		});

		this.separator_3 = new JSeparator();
		this.mnFile.add(this.separator_3);
		this.mnFile.add(this.mntmChangePassword);

		this.separator = new JSeparator();
		this.mnFile.add(this.separator);

		this.mntmExit = new JMenuItem("Exit");
		this.mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmExitActionPerformed(e);
			}
		});
		this.mnFile.add(this.mntmExit);

		this.mnAdd = new JMenu("Add");
		this.menuBar.add(this.mnAdd);

		this.mntmNewStudent = new JMenuItem("New Student");
		this.mntmNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmNewStudentActionPerformed(e);
			}
		});
		this.mnAdd.add(this.mntmNewStudent);

		this.mntmNewLecturer = new JMenuItem("New Lecturer");
		this.mntmNewLecturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmNewLecturerActionPerformed(e);
			}
		});
		this.mnAdd.add(this.mntmNewLecturer);

		this.separator_1 = new JSeparator();
		this.mnAdd.add(this.separator_1);

		this.mntmMarks = new JMenuItem("Marks");
		this.mntmMarks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmMarksActionPerformed(e);
			}
		});
		this.mnAdd.add(this.mntmMarks);

		this.mnFind = new JMenu("Find");
		this.menuBar.add(this.mnFind);

		this.mntmStudent = new JMenuItem("Student");
		this.mntmStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmStudentActionPerformed(e);
			}
		});
		this.mnFind.add(this.mntmStudent);

		this.mntmLecturer = new JMenuItem("Lecturer");
		this.mntmLecturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmLecturerActionPerformed(e);
			}
		});
		this.mnFind.add(this.mntmLecturer);

		this.mnUpdate = new JMenu("Edit");
		this.menuBar.add(this.mnUpdate);

		this.mntmStudent_1 = new JMenuItem("Student");
		this.mntmStudent_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmMain.this.handleMntmStudent_1ActionPerformed(arg0);
			}
		});
		this.mnUpdate.add(this.mntmStudent_1);

		this.mntmLecturer_1 = new JMenuItem("Lecturer");
		this.mntmLecturer_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmLecturer_1ActionPerformed(e);
			}
		});
		this.mnUpdate.add(this.mntmLecturer_1);

		this.separator_4 = new JSeparator();
		this.mnUpdate.add(this.separator_4);

		this.mntmTheme = new JMenuItem("Theme");
		this.mntmTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmThemeActionPerformed(e);
			}
		});
		this.mnUpdate.add(this.mntmTheme);

		this.mnDelete = new JMenu("Delete");
		this.menuBar.add(this.mnDelete);

		this.mntmStudent_2 = new JMenuItem("Student");
		this.mntmStudent_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmStudent_2ActionPerformed(e);
			}
		});
		this.mnDelete.add(this.mntmStudent_2);

		this.mntmLecturer_2 = new JMenuItem("Lecturer");
		this.mntmLecturer_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmLecturer_2ActionPerformed(e);
			}
		});
		this.mnDelete.add(this.mntmLecturer_2);

		this.mnView = new JMenu("View");
		this.menuBar.add(this.mnView);

		this.mntmStudents = new JMenuItem("Students");
		this.mntmStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmStudentsActionPerformed(e);
			}
		});
		this.mnView.add(this.mntmStudents);

		this.mnHelp = new JMenu("Help");
		this.menuBar.add(this.mnHelp);

		this.mntmViewHelp = new JMenuItem("View User Guide");
		this.mntmViewHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmViewHelpActionPerformed(e);
			}
		});

		this.mntmViewUmlDocumentation = new JMenuItem("View UML Documentation");
		this.mntmViewUmlDocumentation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmViewUmlDocumentationActionPerformed(e);
			}
		});
		this.mnHelp.add(this.mntmViewUmlDocumentation);
		this.mnHelp.add(this.mntmViewHelp);

		this.separator_2 = new JSeparator();
		this.mnHelp.add(this.separator_2);

		this.mntmAbout = new JMenuItem("About");
		this.mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMain.this.handleMntmAboutActionPerformed(e);
			}
		});
		this.mnHelp.add(this.mntmAbout);

		this.desktopPane = new JDesktopPane();
		this.setContentPane(this.desktopPane);

		this.setVisible(true);
	}

	public void closeFrames() {
		this.desktopPane.removeAll();
		this.desktopPane.repaint();
	}

	protected void handleMntmLoginActionPerformed(ActionEvent arg0) {
		this.inputHandler.request(Requests.LOGIN);
	}

	protected void handleMntmLogoutActionPerformed(ActionEvent arg0) {
		this.inputHandler.request(Requests.LOGOUT);
	}

	protected void handleMntmChangePasswordActionPerformed(ActionEvent arg0) {
		this.inputHandler.request(Requests.EDIT_PW);
	}

	protected void handleMntmExitActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.EXIT);
	}

	protected void handleMntmNewStudentActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.NEW_STUDENT);

	}

	protected void handleMntmNewLecturerActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.NEW_LECTURER);
	}

	protected void handleMntmMarksActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.ADD_MARK);
	}

	protected void handleMntmStudentActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.VIEW_STUDENT);
	}

	protected void handleMntmLecturerActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.VIEW_LECTURER);
	}

	protected void handleMntmStudentsActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.VIEW_STUDENTS);
	}

	protected void handleMntmViewHelpActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.HELP);
	}

	protected void handleMntmAboutActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.ABOUT);
	}

	protected void handleMntmStudent_1ActionPerformed(ActionEvent arg0) {
		this.inputHandler.request(Requests.EDIT_STUDENT);
	}

	protected void handleMntmLecturer_1ActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.EDIT_LECTURER);
	}

	protected void handleMntmThemeActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.THEME);
	}

	protected void handleMntmStudent_2ActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.DEL_STUDENT);
	}

	protected void handleMntmLecturer_2ActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.DEL_LECTURER);
	}

	protected void handleMntmViewUmlDocumentationActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.UML);
	}
}
