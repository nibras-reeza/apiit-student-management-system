package apiit.nibras.studentms.view.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import apiit.nibras.studentms.controller.models.PanelEnrollmentViewModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelEnrollment extends ExtendedPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9200015321563851255L;
	private JComboBox<String> cmbProgram;
	private JComboBox<String> cmbStream;
	private JList<String> classes;

	private PanelEnrollmentViewModel viewModel;
	private JLabel lblProgram;
	private JLabel lblStream;
	private JLabel lblClasses;

	public PanelEnrollment(PanelEnrollmentViewModel viewModel) {
		this.viewModel = viewModel;
		this.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.GLUE_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.GLUE_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, }));

		this.lblProgram = new JLabel("Program:");
		this.add(this.lblProgram, "3, 2, left, center");

		this.cmbProgram = new JComboBox<String>();
		this.cmbProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PanelEnrollment.this.handleProgramActionPerformed(arg0);
			}
		});

		this.cmbProgram.setModel(viewModel.getPrograms());
		this.cmbProgram.setSelectedIndex(-1);

		this.add(this.cmbProgram, "5, 2, left, center");

		this.lblStream = new JLabel("Stream:");
		this.add(this.lblStream, "3, 4, left, center");

		this.cmbStream = new JComboBox<String>();
		this.cmbStream.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelEnrollment.this.handleStreamActionPerformed(e);
			}
		});
		this.add(this.cmbStream, "5, 4, left, center");

		this.lblClasses = new JLabel("Classes:");
		this.add(this.lblClasses, "3, 6, left, top");

		this.classes = new JList<String>(viewModel.getSubjects());
		JScrollPane scroller = new JScrollPane(this.classes);
		this.add(scroller, "5, 6, left, top");

	}
	/*
		References
		Stackoverflow.com (2013) java - How to set multiple items as selected in JList using setSelectedValue? - Stack Overflow. [online] Available at: http://stackoverflow.com/questions/5961343/how-to-set-multiple-items-as-selected-in-jlist-using-setselectedvalue [Accessed: 7 Jan 2013].
	*/
	
	
	public void present() {
		this.cmbProgram.setSelectedItem(this.viewModel.getProgram());
		this.cmbStream.setSelectedItem(this.viewModel.getStream());
		this.setSelectedValues(this.classes, this.viewModel
				.getSelectedSubjects().toArray());

	}

	private void setSelectedValues(JList<String> list, Object... values) {
		list.clearSelection();
		for (Object value : values) {
			int index = this.getIndex(list.getModel(), value);
			if (index >= 0) {
				list.addSelectionInterval(index, index);
			}
		}
		list.ensureIndexIsVisible(list.getSelectedIndex());
	}

	private int getIndex(ListModel<String> model, Object value) {
		if (value == null)
			return -1;
		if (model instanceof DefaultListModel) {
			return ((DefaultListModel<String>) model).indexOf(value);
		}
		for (int i = 0; i < model.getSize(); i++) {
			if (value.equals(model.getElementAt(i)))
				return i;
		}
		return -1;
	}

	@Override
	public void updateModel() {

		if (this.cmbProgram.getSelectedIndex() != -1) {
			this.viewModel.setProgram((String) this.cmbProgram
					.getSelectedItem());
			this.viewModel.setStream((String) this.cmbStream.getSelectedItem());
			this.viewModel.setSelectedSubjects(this.classes
					.getSelectedValuesList());
		}
	}

	protected void handleProgramActionPerformed(ActionEvent arg0) {
		if (this.cmbProgram.getSelectedIndex() != -1) {

			this.viewModel.setProgram((String) this.cmbProgram
					.getSelectedItem());

			this.cmbStream.setModel(this.viewModel.getStreams());
		}

	}

	protected void handleStreamActionPerformed(ActionEvent e) {
		if (this.cmbStream.getSelectedIndex() != -1) {
			this.viewModel.setStream((String) this.cmbStream.getSelectedItem());
		}
	}

}
