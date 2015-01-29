package apiit.nibras.studentms.view.panels;

import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import apiit.nibras.studentms.controller.models.PanelContactDetailsViewModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelContactDetails extends ExtendedPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1865486250677925536L;
	private JTextField txtStreetAddress_1;
	private JTextField txtStreetAddress_2;
	private JTextField txtCity;
	private JTextField txtEMail;
	private JTextField txtPhone;
	private JComboBox<String> cmbCountry;

	private PanelContactDetailsViewModel viewModel;

	/**
	 * Create the panel.
	 */
	public PanelContactDetails(PanelContactDetailsViewModel viewModel) {
		this.viewModel = viewModel;

		this.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC }));

		JLabel lblStreetAddress = new JLabel("Street Address:");
		this.add(lblStreetAddress, "2, 2, left, default");

		this.txtStreetAddress_1 = new JTextField();
		this.add(this.txtStreetAddress_1, "4, 2, fill, default");
		this.txtStreetAddress_1.setColumns(24);

		this.txtStreetAddress_2 = new JTextField();
		this.add(this.txtStreetAddress_2, "4, 4, fill, default");
		this.txtStreetAddress_2.setColumns(24);

		JLabel lblCity = new JLabel("City:");
		this.add(lblCity, "2, 6, left, default");

		this.txtCity = new JTextField();
		this.add(this.txtCity, "4, 6, fill, default");
		this.txtCity.setColumns(24);

		JLabel lblCountry = new JLabel("Country:");
		this.add(lblCountry, "2, 8, left, default");

		this.cmbCountry = new JComboBox<String>();

		// Generate an array of Strings with country names for country combo
		// box.
		String[] countryCodes = Locale.getISOCountries();
		String[] countries = new String[countryCodes.length];

		for (int i = 0; i < countryCodes.length; i++)
			countries[i] = new Locale(Locale.getDefault().getLanguage(),
					countryCodes[i]).getDisplayCountry();

		this.cmbCountry.setModel(new DefaultComboBoxModel<String>(countries));
		this.cmbCountry.setSelectedIndex(-1);
		this.add(this.cmbCountry, "4, 8, left, default");

		JLabel lblPhone = new JLabel("Phone:");
		this.add(lblPhone, "2, 12, left, default");

		this.txtPhone = new JTextField();
		this.add(this.txtPhone, "4, 12, fill, default");
		this.txtPhone.setColumns(24);

		JLabel lblEmail = new JLabel("Email:");
		this.add(lblEmail, "2, 14, left, default");

		this.txtEMail = new JTextField();
		this.add(this.txtEMail, "4, 14, fill, default");
		this.txtEMail.setColumns(24);

		this.pack();
	}

	@Override
	public void updateModel() {
		this.viewModel.setAddress(this.txtStreetAddress_1.getText() + "\n"
				+ this.txtStreetAddress_2.getText());
		this.viewModel.setCity(this.txtCity.getText());
		this.viewModel.setCountry((String) this.cmbCountry.getSelectedItem());
		this.viewModel.setPhone(this.txtPhone.getText());
		this.viewModel.setEmail(this.txtEMail.getText());
	}

	public void present() {
		String address[] = this.viewModel.getAddress().split("\n");
		this.txtStreetAddress_1.setText(address[0]);
		this.txtStreetAddress_2.setText(address.length > 1 ? address[1] : null);
		this.txtCity.setText(this.viewModel.getCity());
		this.cmbCountry.setSelectedItem(this.viewModel.getCountry());
		this.txtPhone.setText(this.viewModel.getPhone());
		this.txtEMail.setText(this.viewModel.getEmail());
	}

}
