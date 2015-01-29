package apiit.nibras.studentms.view.panels;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class ExtendedPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4466980619335091526L;

	public void pack() {
		this.setMaximumSize(this.getPreferredSize());
	}

	public void setPanelEnabled(boolean status) {
		for (Component c : this.getComponents())
			c.setEnabled(status);
		super.setEnabled(status);
	}

	public void clear() {
		this.clearFields(this);
	}

	public abstract void updateModel();

	public abstract void present();

	@SuppressWarnings("rawtypes")
	private void clearFields(Container container) {
		for (Component c : container.getComponents())
			if (c instanceof JTextField)
				((JTextField) c).setText(null);
			else if (c instanceof JCheckBox) {
				((JCheckBox) c).setSelected(true);
				((JCheckBox) c).doClick();
			} else if (c instanceof JComboBox)
				((JComboBox) c).setSelectedIndex(-1);
			else if (c instanceof Container)
				this.clearFields((Container) c);
	}

	public void setComponentsEditable(boolean status) {
		for (Component c : this.getComponents())
			if (c instanceof JTextField)
				((JTextField) c).setEditable(status);
			else if (c instanceof JComboBox)
				c.setEnabled(status);
			else if (c instanceof JCheckBox)
				c.setEnabled(false);
	}

}