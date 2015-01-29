package apiit.nibras.studentms.view.panels;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import apiit.nibras.studentms.controller.models.PanelTertiaryQualificationViewModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelTertiaryQualification extends ExtendedPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2141590456294618832L;
	private PanelTertiaryQualificationViewModel viewModel;
	private String qualificationType;
	private JCheckBox chckbxTertiaryQualification;
	private JLabel lblDegree;
	private JLabel lblUniversity;
	private JTextField txtDegree;
	private JTextField txtUniversity;
	private JLabel lblGpa;
	private JTextField txtGPA;

	public PanelTertiaryQualification(
			PanelTertiaryQualificationViewModel viewModel) {
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
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, }));

		this.chckbxTertiaryQualification = new JCheckBox(this.qualificationType);
		this.chckbxTertiaryQualification
				.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						PanelTertiaryQualification.this
								.updateComponentsEnabledState();
					}
				});
		this.add(this.chckbxTertiaryQualification, "3, 2, default, center");

		this.lblDegree = new JLabel("Degree:");
		this.add(this.lblDegree, "3, 4, left, center");

		this.txtDegree = new JTextField();
		this.add(this.txtDegree, "5, 4, left, center");
		this.txtDegree.setColumns(16);

		this.lblUniversity = new JLabel("University:");
		this.add(this.lblUniversity, "3, 6, left, center");

		this.txtUniversity = new JTextField();
		this.add(this.txtUniversity, "5, 6, left, center");
		this.txtUniversity.setColumns(16);

		this.lblGpa = new JLabel("GPA:");
		this.add(this.lblGpa, "3, 8, left, center");

		this.txtGPA = new JTextField();
		this.add(this.txtGPA, "5, 8, left, center");
		this.txtGPA.setColumns(4);

		this.present();

		this.updateComponentsEnabledState();
		this.pack();
	}

	private void updateComponentsEnabledState() {
		this.viewModel.setEmpty(!this.chckbxTertiaryQualification.isSelected());
		for (Component c : this.getComponents())
			if (c != this.chckbxTertiaryQualification)
				c.setEnabled(this.chckbxTertiaryQualification.isSelected());
		this.setEnabled(this.chckbxTertiaryQualification.isSelected());
	}

	public void present() {
		if (!this.viewModel.isEmpty()) {
			this.chckbxTertiaryQualification.setSelected(true);
			this.chckbxTertiaryQualification.setText(this.viewModel.getLevel());
			this.txtDegree.setText(this.viewModel.getDegree());
			this.txtUniversity.setText(this.viewModel.getUniversity());
			this.txtGPA.setText(this.viewModel.getGpa());
		}
	}

	@Override
	public void updateModel() {

		this.viewModel.setEmpty(!this.chckbxTertiaryQualification.isSelected());
		this.viewModel.setDegree(this.txtDegree.getText());
		this.viewModel.setUniversity(this.txtUniversity.getText());
		this.viewModel.setGpa(this.txtGPA.getText());
	}

}