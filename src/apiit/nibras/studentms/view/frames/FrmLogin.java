package apiit.nibras.studentms.view.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import apiit.nibras.studentms.controller.InputHandler;
import apiit.nibras.studentms.controller.Requests;
import apiit.nibras.studentms.controller.models.FrmLoginViewModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class FrmLogin extends JDialog {

	private static final long serialVersionUID = 916075389327509750L;
	private JTextField txtUserName;
	private JPasswordField txtPassword;

	private FrmLoginViewModel viewModel;
	private InputHandler inputHandler;

	/**
	 * Create the dialog.
	 */
	public FrmLogin(InputHandler inputHandler, FrmLoginViewModel viewModel,
			JFrame parent) {
		super(parent);

		this.inputHandler = inputHandler;
		this.viewModel = viewModel;

		this.setModal(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setAutoRequestFocus(true);
		this.setTitle("Login");
		this.setBounds(100, 100, 450, 300);

		JPanel loginForm = new JPanel();
		this.getContentPane().add(loginForm, BorderLayout.CENTER);
		loginForm.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC, }));

		JLabel lblUserName = new JLabel("User Name:");
		loginForm.add(lblUserName, "2, 2, left, center");

		this.txtUserName = new JTextField();
		loginForm.add(this.txtUserName, "4, 2, left, center");
		this.txtUserName.setColumns(16);

		JLabel lblPassword = new JLabel("Password:");
		loginForm.add(lblPassword, "2, 4, left, center");

		this.txtPassword = new JPasswordField();
		this.txtPassword.setColumns(16);
		loginForm.add(this.txtPassword, "4, 4, left, center");

		JPanel loginButtons = new JPanel();
		this.getContentPane().add(loginButtons, BorderLayout.SOUTH);
		loginButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmLogin.this.handleBtnLoginActionPerformed(arg0);
			}
		});
		
		this.getRootPane().setDefaultButton(btnLogin);
		loginButtons.add(btnLogin);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmLogin.this.handleBtnClearActionPerformed(e);
			}
		});
		loginButtons.add(btnClear);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmLogin.this.handleBtnExitActionPerformed(e);
			}
		});
		loginButtons.add(btnExit);

		this.setAlwaysOnTop(true);

		this.pack();

		this.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	public void updateModel() {
		this.viewModel.setUserName(this.txtUserName.getText());
		this.viewModel.setPassword(this.txtPassword.getText());
	}

	protected void handleBtnLoginActionPerformed(ActionEvent arg0) {
		this.inputHandler.login(this, this.viewModel);
	}

	protected void handleBtnClearActionPerformed(ActionEvent e) {
		this.txtUserName.setText(null);
		this.txtPassword.setText(null);
	}

	protected void handleBtnExitActionPerformed(ActionEvent e) {
		this.inputHandler.request(Requests.EXIT);
	}
}
