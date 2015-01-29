package apiit.nibras.studentms.view.frames;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import apiit.nibras.studentms.controller.InputHandler;
import apiit.nibras.studentms.controller.models.FrmStudentsViewModel;
import apiit.nibras.studentms.controller.models.TableStudentsModel;

public class FrmStudents extends ExtendedFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4769536507132970978L;

	private FrmStudentsViewModel viewModel;
	private InputHandler inputHandler;

	private TableStudentsModel tableModel;
	private JTable table;

	public FrmStudents(InputHandler inputHandler, FrmStudentsViewModel viewModel) {
		this.inputHandler = inputHandler;
		this.viewModel = viewModel;

		this.setTitle("View All Students");
		this.setMaximizable(true);
		this.setClosable(true);

		this.tableModel = viewModel.getTableModel();

		// http://stackoverflow.com/questions/6104916/how-to-make-jtable-both-autoresize-and-horizontall-scrollable
		this.table = new JTable(this.tableModel) {

			private static final long serialVersionUID = -8733245431247778L;

			public boolean getScrollableTracksViewportWidth() {
				return this.getPreferredSize().width < this.getParent()
						.getWidth();
			}

		};

		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		this.table.addMouseListener(

		new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				FrmStudents.this.handleJTableDoubleClicked(e);
			}

		}

		);

		this.table.setAutoCreateRowSorter(true);

		JScrollPane scroller = new JScrollPane(this.table);
		this.add(scroller);

		this.pack();

		this.setVisible(true);

	}

	private void handleJTableDoubleClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			this.viewModel.setId((String) this.tableModel.getValueAt(
					((JTable) e.getSource()).getSelectedRow(), 0));
			this.inputHandler.view(this, this.viewModel);
		}

	}
}
