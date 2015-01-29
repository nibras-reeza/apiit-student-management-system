package apiit.nibras.studentms.view.frames;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class FrmTheme extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5239887231891538045L;

	private JFrame parent;
	private JPanel panel;
	private JPanel buttons;
	private JLabel lblTheme;
	private JComboBox<String> comboBox;
	private JButton btnChange;
	private JButton btnCancle;

	public FrmTheme(JFrame parent) {
		this.parent = parent;
		this.setTitle("Change Theme");
		this.setClosable(true);
		this.getContentPane().setLayout(
				new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		this.panel = new JPanel();
		this.getContentPane().add(this.panel);
		this.panel
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		this.lblTheme = new JLabel("Theme:");
		this.panel.add(this.lblTheme, "2, 2, right, center");

		/*
		
			References

			Docs.oracle.com (n.d.) UIManager (Java 2 Platform SE v1.4.2). [online] Available at: http://docs.oracle.com/javase/1.4.2/docs/api/javax/swing/UIManager.html#getAuxiliaryLookAndFeels%28%29 [Accessed: 7 Jan 2013].

			Docs.oracle.com (1995) How to Set the Look and Feel (The Java™ Tutorials > Creating a GUI With JFC/Swing > Modifying the Look and Feel). [online] Available at: http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html#dynamic [Accessed: 7 Jan 2013].

					
		*/

		this.comboBox = new JComboBox<String>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
			model.addElement(info.getName());
		this.comboBox.setModel(model);
		this.panel.add(this.comboBox, "4, 2, left, center");

		this.buttons = new JPanel();

		this.getContentPane().add(this.buttons);
		this.buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		this.btnChange = new JButton("Change");
		this.btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTheme.this.handleBtnChangeActionPerformed(arg0);
			}
		});
		this.buttons.add(this.btnChange);

		this.btnCancle = new JButton("Cancel");
		this.btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmTheme.this.handleBtnCancleActionPerformed(arg0);
			}
		});
		this.buttons.add(this.btnCancle);

		this.pack();
		this.setVisible(true);

	}

	protected void handleBtnCancleActionPerformed(ActionEvent arg0) {
		this.dispose();
	}

	protected void handleBtnChangeActionPerformed(ActionEvent arg0) {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
			if (info.getName().equals(this.comboBox.getSelectedItem()))
				try {
					UIManager.setLookAndFeel(info.getClassName());
					SwingUtilities.updateComponentTreeUI(this.parent);
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					JOptionPane.showInternalInputDialog(this,
							"Failed to set theme. Try another theme!",
							"Failiure! :(", JOptionPane.ERROR_MESSAGE);
				}
	}
}
