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
import apiit.nibras.studentms.controller.models.FrmLecturerViewModel;
import apiit.nibras.studentms.view.panels.ExtendedPanel;
import apiit.nibras.studentms.view.panels.PanelContactDetails;
import apiit.nibras.studentms.view.panels.PanelEnlist;
import apiit.nibras.studentms.view.panels.PanelPersonalDetails;
import apiit.nibras.studentms.view.panels.PanelSecondaryQualification;
import apiit.nibras.studentms.view.panels.PanelTertiaryQualification;

public class FrmLecturer extends ExtendedFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2450430590855211915L;
	private FrmLecturerViewModel viewModel;
	private String type;

	private JPanel lecturer;
	private JPanel qualifications;
	private JPanel enlist;
	private JPanel buttons;

	private JTabbedPane tabbedPane;

	private JButton btnAdd;
	private JButton btnClear;
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnCancel;

	public FrmLecturer(String type, InputHandler inputHandler,
			FrmLecturerViewModel viewModel) {
		super();

		this.type = type;
		this.viewModel = viewModel;
		this.inputHandler = inputHandler;

		if (type.equals("create"))
			this.setTitle("Add new Lecturer - " + viewModel.getId());
		else if (type.equals("view"))
			this.setTitle("View Lecturer - " + viewModel.getId());
		else if (type.equals("update"))
			this.setTitle("Edit Lecturer - " + viewModel.getId());

		this.getContentPane().setLayout(
				new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.initPanelLecturer();

		this.initPanelQualifications();
		this.initPanelEnlist();

		this.initPanelButtons();

		this.tabbedPane = new JTabbedPane();
		this.tabbedPane.add(this.lecturer, "Lecturer");

		this.tabbedPane.add(this.qualifications, "Qualifications");
		this.tabbedPane.add(this.enlist, "Enlist");

		this.getContentPane().add(this.tabbedPane);
		this.getContentPane().add(this.buttons);

		this.setClosable(true);

		this.pack();

		this.setVisible(true);

	}

	private void initPanelEnlist() {
		this.enlist = new JPanel();
		this.enlist.setLayout(new BoxLayout(this.enlist, BoxLayout.Y_AXIS));

		PanelEnlist enlist = new PanelEnlist(this.viewModel.getEnlistVM());
		this.enlist.add(enlist);
	}

	private void initPanelLecturer() {
		this.lecturer = new JPanel();
		this.lecturer.setLayout(new BoxLayout(this.lecturer, BoxLayout.Y_AXIS));
		PanelPersonalDetails lecturerPD = new PanelPersonalDetails(
				this.viewModel.getLecturerPDVM());
		PanelContactDetails lecturerCD = new PanelContactDetails(
				this.viewModel.getLecturerCDVM());
		this.lecturer.add(lecturerPD);
		this.lecturer.add(lecturerCD);
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
					FrmLecturer.this.handleBtnAddActionPerformed(arg0);
				}
			});

			this.btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FrmLecturer.this.handleBtnClearActionPerformed(arg0);
				}
			});
			this.buttons.add(this.btnAdd);
			this.buttons.add(this.btnClear);
		} else if (this.type.equals("view")) {
			this.btnModify = new JButton("Modify");
			this.btnModify.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FrmLecturer.this.handleBtnModifyActionPerformed(arg0);
				}
			});

			this.btnDelete = new JButton("Delete");
			this.btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FrmLecturer.this.handleBtnDeleteActionPerformed(arg0);
				}
			});

			this.buttons.add(this.btnModify);
			this.buttons.add(this.btnDelete);
		} else if (this.type.equals("update")) {
			this.btnUpdate = new JButton("Update");

			this.btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FrmLecturer.this.handleBtnUpdateActionPerformed(arg0);
				}
			});

			this.buttons.add(this.btnUpdate);
		}

		this.btnCancel = new JButton("Cancel");
		this.btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmLecturer.this.handleBtnCancelActionPerformed(arg0);
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
