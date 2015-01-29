package apiit.nibras.studentms.view.frames;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import apiit.nibras.studentms.controller.InputHandler;
import apiit.nibras.studentms.controller.models.FrmStudentViewModel;
import apiit.nibras.studentms.view.panels.ExtendedPanel;
import apiit.nibras.studentms.view.panels.PanelContactDetails;
import apiit.nibras.studentms.view.panels.PanelEnrollment;
import apiit.nibras.studentms.view.panels.PanelMarks;
import apiit.nibras.studentms.view.panels.PanelPersonalDetails;
import apiit.nibras.studentms.view.panels.PanelSecondaryQualification;
import apiit.nibras.studentms.view.panels.PanelTertiaryQualification;

public class FrmStudent extends ExtendedFrame {

	private static final long serialVersionUID = 2602415692973413499L;

	private FrmStudentViewModel viewModel;
	private String type;

	private JPanel student;
	private JPanel parent;
	private JPanel qualifications;
	private JPanel enrollment;
	protected JPanel results;
	private JPanel buttons;

	private JTabbedPane tabbedPane;

	private JButton btnAdd;
	private JButton btnClear;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnCancel;

	public FrmStudent(String type, InputHandler inputHandler,
			FrmStudentViewModel viewModel) {
		super();

		this.type = type;
		this.viewModel = viewModel;
		this.inputHandler = inputHandler;

		if (type.equals("create"))
			this.setTitle("Add new Student - " + viewModel.getId());
		else if (type.equals("view"))
			this.setTitle("View Student - " + viewModel.getId());
		else if (type.equals("update"))
			this.setTitle("Edit Student - " + viewModel.getId());

		this.getContentPane().setLayout(
				new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.initPanelStudent();
		this.initPanelParent();
		this.initPanelQualifications();
		this.initPanelEnrollment();

		this.initPanelButtons();

		this.tabbedPane = new JTabbedPane();
		this.tabbedPane.add(this.student, "Student");
		this.tabbedPane.add(this.parent, "Parent");
		this.tabbedPane.add(this.qualifications, "Qualifications");
		this.tabbedPane.add(this.enrollment, "Enrollment");

		if (type.equals("view")) {
			this.initPanelResults();
			this.tabbedPane.add(this.results, "Results");
		}

		this.getContentPane().add(this.tabbedPane);
		this.getContentPane().add(this.buttons);

		this.setClosable(true);

		this.pack();

		this.setVisible(true);

	}

	private void initPanelResults() {
		this.results = new PanelMarks(this.viewModel.getMarksVM());

	}

	private void initPanelEnrollment() {
		this.enrollment = new JPanel();
		this.enrollment.setLayout(new BoxLayout(this.enrollment,
				BoxLayout.Y_AXIS));

		PanelEnrollment enrollment = new PanelEnrollment(
				this.viewModel.getEnrollVM());
		this.enrollment.add(enrollment);
	}

	private void initPanelStudent() {
		this.student = new JPanel();
		this.student.setLayout(new BoxLayout(this.student, BoxLayout.Y_AXIS));
		PanelPersonalDetails studentPD = new PanelPersonalDetails(
				this.viewModel.getStudentPDVM());
		PanelContactDetails studentCD = new PanelContactDetails(
				this.viewModel.getStudentCDVM());
		this.student.add(studentPD);
		this.student.add(studentCD);
	}

	private void initPanelParent() {
		this.parent = new JPanel();
		this.parent.setLayout(new BoxLayout(this.parent, BoxLayout.Y_AXIS));
		PanelPersonalDetails parentPD = new PanelPersonalDetails(
				this.viewModel.getParentPDVM());
		PanelContactDetails parentCD = new PanelContactDetails(
				this.viewModel.getParentCDVM());
		this.parent.add(parentPD);
		this.parent.add(parentCD);
	}

	private void initPanelQualifications() {
		this.qualifications = new JPanel();
		this.qualifications.setLayout(new BoxLayout(this.qualifications,
				BoxLayout.Y_AXIS));

		PanelSecondaryQualification panelOL = new PanelSecondaryQualification(
				this.viewModel.getOL_VM());
		PanelSecondaryQualification panelAL = new PanelSecondaryQualification(
				this.viewModel.getAL_VM());
		PanelTertiaryQualification panelUG = new PanelTertiaryQualification(
				this.viewModel.getUG_VM());

		this.qualifications.add(panelAL);
		this.qualifications.add(panelUG);
		this.qualifications.add(panelOL);
	}

	private void initPanelButtons() {
		this.buttons = new JPanel();
		this.buttons.setLayout(new FlowLayout());

		if (this.type.equals("create")) {
			this.btnAdd = new JButton("Add");
			this.btnClear = new JButton("Clear");

			this.btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FrmStudent.this.handleBtnAddActionPerformed(arg0);
				}
			});

			this.btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FrmStudent.this.handleBtnClearActionPerformed(arg0);
				}
			});
			this.buttons.add(this.btnAdd);
			this.buttons.add(this.btnClear);
		} else if (this.type.equals("view")) {
			this.btnModify = new JButton("Modify");
			this.btnModify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FrmStudent.this.handleBtnModifyActionPerformed(arg0);
				}
			});

			this.btnDelete = new JButton("Delete");
			this.btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FrmStudent.this.handleBtnDeleteActionPerformed(arg0);
				}
			});

			this.buttons.add(this.btnModify);
			this.buttons.add(this.btnDelete);
		} else if (this.type.equals("update")) {
			this.btnUpdate = new JButton("Update");

			this.btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FrmStudent.this.handleBtnUpdateActionPerformed(arg0);
				}
			});

			this.buttons.add(this.btnUpdate);
		}

		this.btnCancel = new JButton("Cancel");
		this.btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmStudent.this.handleBtnCancelActionPerformed(arg0);
			}
		});
		this.buttons.add(this.btnCancel);

	}

	protected void handleBtnUpdateActionPerformed(ActionEvent arg0) {
		this.updateModel(this);
		this.inputHandler.update(this, this.viewModel);
	}

	public void present() {
		this.present(this);
	}

	void present(Component component) {
		for (Component c : ((Container) component).getComponents())
			if (c instanceof ExtendedPanel)
				((ExtendedPanel) c).present();
			else if (c instanceof Container)
				this.present(c);
	}

	void updateModel(Component component) {
		for (Component c : ((Container) component).getComponents())
			if (c instanceof ExtendedPanel)
				((ExtendedPanel) c).updateModel();
			else if (c instanceof Container)
				this.updateModel(c);

	}

	void handleBtnAddActionPerformed(ActionEvent arg0) {
		this.updateModel(this);
		this.inputHandler.add(this, this.viewModel);

	}

	void handleBtnClearActionPerformed(ActionEvent arg0) {
		this.clear();
	}

	void handleBtnDeleteActionPerformed(ActionEvent arg0) {
		this.inputHandler.remove(this, this.viewModel);

	}

	void handleBtnModifyActionPerformed(ActionEvent arg0) {
		this.inputHandler.update(this, this.viewModel);

	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	void handleBtnCancelActionPerformed(ActionEvent arg0) {
		this.close();
	}

}