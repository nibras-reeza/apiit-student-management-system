package apiit.nibras.studentms.view.frames;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import apiit.nibras.studentms.controller.InputHandler;
import apiit.nibras.studentms.controller.models.FrmAddMarksViewModel;

public class FrmAddMarks extends ExtendedFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7835491577639987318L;
	private JComboBox<String> cmbSubject;
	private JScrollPane scroller;
	private FrmAddMarksViewModel viewModel;
	private InputHandler inputHandler;

	private List<JLabel> lblStudents;
	private List<JTextField> txtMarks;

	private JPanel panelMain;

	private JPanel panelButtons;

	public FrmAddMarks(InputHandler inputHandler, FrmAddMarksViewModel viewModel) {
		this.setNormalBounds(new Rectangle(10, 10, 400, 300));
		this.setTitle("Add Marks");
		this.setResizable(true);
		this.setClosable(true);
		this.inputHandler = inputHandler;
		this.viewModel = viewModel;
		this.getContentPane().setLayout(
				new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.panelMain = new JPanel();
		this.panelMain
				.setLayout(new BoxLayout(this.panelMain, BoxLayout.Y_AXIS));
		this.panelButtons = new JPanel();


		this.lblStudents = new ArrayList<JLabel>();
		this.txtMarks = new ArrayList<JTextField>();

		this.cmbSubject = new JComboBox<String>();
		this.cmbSubject.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				FrmAddMarks.this.handleCmbSubjectItemStateChanged(arg0);
			}
		});
		this.cmbSubject.setModel(viewModel.getSubjects());
		this.cmbSubject.setSelectedIndex(-1);
		this.getContentPane().add(this.cmbSubject);

		this.scroller = new JScrollPane(this.panelMain);
		this.getContentPane().add(this.scroller);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmAddMarks.this.handleBtnSubmitActionPerformed(arg0);
			}
		});
		JButton btnCanel = new JButton("Cancel");
		btnCanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmAddMarks.this.handleBtnCanelActionPerformed(e);
			}
		});

		this.panelButtons.add(btnSubmit);
		this.panelButtons.add(btnCanel);

		this.getContentPane().add(this.panelButtons);

		this.pack();
		this.setVisible(true);

	}

	public void updateModel() {
		boolean invalid = false;
		for (JTextField txt : this.txtMarks)
			if (txt.getText().matches("[A-Za-z]+")) {
				JOptionPane.showMessageDialog(this,
						"Marks cannot contain letters!", "Invalid Score!",
						JOptionPane.WARNING_MESSAGE);
				invalid = true;
			}
		if (!invalid)
			for (int i = 0; i < this.lblStudents.size(); i++) {
				this.viewModel.setScore(this.lblStudents.get(i).getText(),
						Double.parseDouble(this.txtMarks.get(i).getText()));
			}
	}

	protected void handleBtnSubmitActionPerformed(ActionEvent arg0) {
		this.inputHandler.submit(this, this.viewModel);
	}

	protected void handleBtnCanelActionPerformed(ActionEvent e) {
		this.dispose();
	}

	protected void handleCmbSubjectItemStateChanged(ItemEvent arg0) {

		// this.scroller = new JScrollPane(this.panelMain);

		if (arg0.getStateChange() == ItemEvent.SELECTED
				&& this.cmbSubject.getSelectedIndex() != -1) {
			this.viewModel.setSubject((String) this.cmbSubject
					.getSelectedItem());
			this.panelMain.removeAll();

			this.lblStudents.clear();
			this.txtMarks.clear();
			for (String s : this.viewModel.getStudents()) {
				JPanel panelStudent = new JPanel();
				JLabel lblStudent = new JLabel(s);
				JTextField txtMark = new JTextField();
				txtMark.setColumns(5);

				this.lblStudents.add(lblStudent);
				this.txtMarks.add(txtMark);

				panelStudent.add(lblStudent);
				panelStudent.add(txtMark);

				this.panelMain.add(panelStudent);

			}
			this.updateUI();
			this.pack();
		}
	}
}
