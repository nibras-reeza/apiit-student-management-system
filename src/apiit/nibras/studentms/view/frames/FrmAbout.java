package apiit.nibras.studentms.view.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FrmAbout extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4886315729870773785L;
	private JLabel lblStudentManagementSystem;
	private JPanel panel;
	private JLabel lblAuthor;
	private JLabel lblNibrasAhamedReeza;
	private JLabel lblCfcom;
	private JLabel lblApiit;

	public FrmAbout() {
		this.getContentPane().setBackground(Color.WHITE);
		this.setTitle("About");
		this.setBackground(Color.WHITE);
		this.setClosable(true);

		this.setBounds(100, 100, 450, 300);

		this.lblStudentManagementSystem = new JLabel(
				"Student Management System");
		this.lblStudentManagementSystem.setBackground(Color.WHITE);
		this.lblStudentManagementSystem
				.setFont(new Font("Arial", Font.BOLD, 16));
		this.lblStudentManagementSystem
				.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(this.lblStudentManagementSystem,
				BorderLayout.NORTH);

		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		this.getContentPane().add(this.panel, BorderLayout.SOUTH);
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));

		this.lblAuthor = new JLabel("Author:");
		this.panel.add(this.lblAuthor);

		this.lblNibrasAhamedReeza = new JLabel("Nibras Ahamed Reeza (CB004641)");
		this.panel.add(this.lblNibrasAhamedReeza);

		this.lblCfcom = new JLabel("CF1221COM");
		this.panel.add(this.lblCfcom);

		this.lblApiit = new JLabel("APIIT");
		this.panel.add(this.lblApiit);

		this.pack();
		this.setVisible(true);

	}

}
