package apiit.nibras.studentms.view.frames;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JInternalFrame;

import apiit.nibras.studentms.controller.InputHandler;
import apiit.nibras.studentms.view.panels.ExtendedPanel;

public class ExtendedFrame extends JInternalFrame {

	private static final long serialVersionUID = 1881733135297800706L;
	protected InputHandler inputHandler;

	public void setEnabled(boolean status) {
		this.setComponentsEditable(this, status);
	}

	private void setComponentsEditable(Container container, boolean status) {
		for (Component c : container.getComponents())
			if (c instanceof ExtendedPanel)
				((ExtendedPanel) c).setComponentsEditable(status);
			else if (c instanceof Container)
				this.setComponentsEditable((Container) c, status);
	}

	public void clear() {
		this.clearContainedFields(this);
	}

	public void close() {
		this.dispose();
	}

	protected void clearContainedFields(Container container) {
		for (Component c : container.getComponents())
			if (c instanceof ExtendedPanel)
				((ExtendedPanel) c).clear();
			else if (c instanceof Container)
				this.clearContainedFields((Container) c);
	}

}
