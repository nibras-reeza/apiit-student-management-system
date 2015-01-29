package apiit.nibras.studentms.view.panels;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import apiit.nibras.studentms.controller.models.PanelMarksViewModel;

public class PanelMarks extends ExtendedPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7835491577639987318L;

	private PanelMarksViewModel viewModel;

	private JTextArea report;

	public PanelMarks(PanelMarksViewModel viewModel) {
		this.viewModel = viewModel;
		this.report = new JTextArea();

		this.report.setColumns(30);
		this.report.setRows(10);

		this.report.setEditable(false);

		JScrollPane scroller = new JScrollPane(this.report);

		this.add(scroller);

	}

	@Override
	public void updateModel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void present() {
		this.report.append(this.viewModel.getReport());
	}

}
