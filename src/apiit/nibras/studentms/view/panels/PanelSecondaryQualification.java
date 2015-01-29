package apiit.nibras.studentms.view.panels;

import java.awt.Component;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import apiit.nibras.studentms.controller.models.PanelSecondaryQualificationViewModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelSecondaryQualification extends ExtendedPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6826118574417752388L;
	private String qualificationType;
	private JCheckBox chckbxSecondaryQualification;
	private JRadioButton rdbtnLocal;
	private JRadioButton rdbtnLondon;
	private JLabel lblStream;
	private JLabel lblResults;
	private JTextField txtStream;
	private JTextField txtResults;
	private ButtonGroup board;
	private JTextField txtSchool;
	private JLabel lblSchool;
	private PanelSecondaryQualificationViewModel viewModel;

	public PanelSecondaryQualification(
			PanelSecondaryQualificationViewModel viewModel) {
		this.viewModel = viewModel;
		this.qualificationType = this.viewModel.getLevel();

		this.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GLUE_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.GLUE_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, }));

		this.chckbxSecondaryQualification = new JCheckBox(
				this.qualificationType);
		this.chckbxSecondaryQualification
				.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						PanelSecondaryQualification.this
								.updateComponentsEnabledState();
					}
				});
		this.add(this.chckbxSecondaryQualification, "3, 2, default, center");

		this.board = new ButtonGroup();

		this.rdbtnLocal = new JRadioButton("Local");
		this.board.add(this.rdbtnLocal);
		this.add(this.rdbtnLocal, "3, 4, left, center");

		this.rdbtnLondon = new JRadioButton("London");
		this.board.add(this.rdbtnLondon);
		this.add(this.rdbtnLondon, "5, 4, left, center");

		this.lblSchool = new JLabel("School:");
		this.add(this.lblSchool, "3, 6, left, center");

		this.txtSchool = new JTextField();
		this.add(this.txtSchool, "5, 6, left, center");
		this.txtSchool.setColumns(16);

		this.lblStream = new JLabel("Stream:");
		this.add(this.lblStream, "3, 8, left, center");

		this.txtStream = new JTextField();
		this.add(this.txtStream, "5, 8, left, center");
		this.txtStream.setColumns(16);

		this.lblResults = new JLabel("Results:");
		this.add(this.lblResults, "3, 10, left, center");

		this.txtResults = new JTextField();
		this.add(this.txtResults, "5, 10, left, center");
		this.txtResults.setColumns(16);

		this.present();
		this.updateComponentsEnabledState();
		this.pack();
	}

	private void updateComponentsEnabledState() {
		this.viewModel
				.setEmpty(!this.chckbxSecondaryQualification.isSelected());
		for (Component c : this.getComponents())
			if (c != this.chckbxSecondaryQualification)
				c.setEnabled(this.chckbxSecondaryQualification.isSelected());
		this.setEnabled(this.chckbxSecondaryQualification.isSelected());
	}

	public void clear() {
		this.board.clearSelection();
		super.clear();
	}

	public void present() {
		if (!this.viewModel.isEmpty()) {
			this.chckbxSecondaryQualification.setSelected(true);
			this.chckbxSecondaryQualification
					.setText(this.viewModel.getLevel());

			this.txtStream.setText(this.viewModel.getStream());
			this.txtResults.setText(this.viewModel.getResults());
			if (this.viewModel.getBoard().equals("London"))
				this.rdbtnLondon.setSelected(true);
			else if (this.viewModel.getBoard().equals("Local"))
				this.rdbtnLocal.setSelected(true);
			this.txtSchool.setText(this.viewModel.getSchool());
		}

	}

	@Override
	public void updateModel() {
		this.viewModel
				.setEmpty(!this.chckbxSecondaryQualification.isSelected());
		this.viewModel.setSchool(this.txtSchool.getText());

		this.viewModel.setResults(this.txtResults.getText());
		this.viewModel.setStream(this.txtStream.getText());

		if (this.rdbtnLondon.isSelected())
			this.viewModel.setBoard(this.rdbtnLondon.getText());
		else if (this.rdbtnLocal.isSelected())
			this.viewModel.setBoard(this.rdbtnLocal.getText());

	}

}
