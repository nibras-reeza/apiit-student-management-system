package apiit.nibras.studentms.view.panels;

import java.awt.FlowLayout;
import java.text.DateFormatSymbols;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import apiit.nibras.studentms.controller.models.PanelPersonalDetailsViewModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelPersonalDetails extends ExtendedPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6785968339679040352L;

	private PanelPersonalDetailsViewModel viewModel;

	private JTextField txtFirstName;
	private JTextField txtLastName;
	private GregorianCalendar calendar;
	private JComboBox<String> cmbYear;
	private JPanel grpGender;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private ButtonGroup gender;
	private JPanel grpDoB;
	private JComboBox<String> cmbMonth;

	private JComboBox<String> cmbDate;

	/**
	 * Create the panel.
	 */
	public PanelPersonalDetails(PanelPersonalDetailsViewModel viewModel) {

		this.viewModel = viewModel;

		this.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC, },
				new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC, }));

		JLabel lblFirstName = new JLabel("First Name:");
		this.add(lblFirstName, "2, 2, left, default");

		this.txtFirstName = new JTextField();
		this.add(this.txtFirstName, "4, 2, left, default");
		this.txtFirstName.setColumns(24);

		JLabel lblLastName = new JLabel("Last Name:");
		this.add(lblLastName, "2, 4, left, default");

		this.txtLastName = new JTextField();
		this.txtLastName.setColumns(24);
		this.add(this.txtLastName, "4, 4, left, default");

		this.grpGender = new JPanel();
		this.grpGender.setBorder(new TitledBorder(null, "Gender:",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.add(this.grpGender, "2, 6, fill, top");
		this.grpGender
				.setLayout(new BoxLayout(this.grpGender, BoxLayout.Y_AXIS));

		this.rdbtnMale = new JRadioButton("Male");
		this.grpGender.add(this.rdbtnMale);

		this.rdbtnFemale = new JRadioButton("Female");
		this.grpGender.add(this.rdbtnFemale);

		this.gender = new ButtonGroup();
		this.gender.add(this.rdbtnFemale);
		this.gender.add(this.rdbtnMale);

		this.grpDoB = new JPanel();
		this.grpDoB.setBorder(new TitledBorder(null, "Date of Birth:",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.add(this.grpDoB, "4, 6, fill, center");
		this.grpDoB.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		/*
			References
			Docs.oracle.com (1995) Creating a Locale (The Java™ Tutorials > Internationalization > Setting the Locale). [online] Available at: http://docs.oracle.com/javase/tutorial/i18n/locale/create.html#constructors [Accessed: 7 Jan 2013].

			Stackoverflow.com (2013) java - How to set multiple items as selected in JList using setSelectedValue? - Stack Overflow. [online] Available at: http://stackoverflow.com/questions/5961343/how-to-set-multiple-items-as-selected-in-jlist-using-setselectedvalue [Accessed: 7 Jan 2013].

			Stackoverflow.com (n.d.) java - How do I iterate over each Entry in a Map? - Stack Overflow. [online] Available at: http://stackoverflow.com/questions/46898/how-do-i-iterate-over-each-entry-in-a-map [Accessed: 7 Jan 2013].

			Unknown. (2013) [online] Available at: http://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html#getDisplayNames%28int,%20int,%20java.util.Locale%29 [Accessed: 7 Jan 2013].
		*/
		
		this.calendar = new GregorianCalendar();

		JLabel lblDay = new JLabel("Day:");
		this.grpDoB.add(lblDay);

		this.cmbDate = new JComboBox<String>();

		for (int i = 1; i < 32; i++)
			this.cmbDate.addItem(Integer.toString(i));

		this.cmbDate.setSelectedIndex(-1);
		this.grpDoB.add(this.cmbDate);

		JLabel lblMonth = new JLabel("Month:");
		this.grpDoB.add(lblMonth);

		this.cmbMonth = new JComboBox<String>();

		// http://stackoverflow.com/questions/1038570/how-can-i-convert-an-integer-to-localized-month-name-in-java
		// http://docs.oracle.com/javase/6/docs/api/java/util/Map.html#values%28%29

		String months[] = new String[12];
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
		String[] monthsTemp = dateFormatSymbols.getMonths();

		System.arraycopy(monthsTemp, 0, months, 0, months.length);
		// Doing a temporary conversion because getMonths returns a 13 element
		// array
		// with the last element being empty.

		this.cmbMonth.setModel(new DefaultComboBoxModel<String>(months));
		this.cmbMonth.setSelectedIndex(-1);
		this.grpDoB.add(this.cmbMonth);

		JLabel lblYear = new JLabel("Year:");
		this.grpDoB.add(lblYear);

		this.cmbYear = new JComboBox<String>();

		for (int i = 0, y = this.calendar.get(GregorianCalendar.YEAR) - 14; i < 100; y--, i++)
			this.cmbYear.addItem(Integer.toString(y));

		this.cmbYear.setSelectedIndex(-1);
		this.grpDoB.add(this.cmbYear);
		this.pack();
	}

	public void clear() {
		this.gender.clearSelection();
		super.clear();
	}

	public void present() {
		this.cmbDate.setSelectedItem(this.viewModel.getDobDay());
		this.cmbMonth.setSelectedItem(this.viewModel.getDobMonth());
		this.cmbYear.setSelectedItem(this.viewModel.getDobYear());

		this.txtFirstName.setText(this.viewModel.getFirstName());
		this.txtLastName.setText(this.viewModel.getLastName());

		if (this.viewModel.getGender().equals("Male"))
			this.rdbtnMale.setSelected(true);
		else if (this.viewModel.getGender().equals("Female"))
			this.rdbtnFemale.setSelected(true);

	}

	@Override
	public void updateModel() {
		this.viewModel.setDobDay((String) this.cmbDate.getSelectedItem());
		this.viewModel.setDobMonth((String) this.cmbMonth.getSelectedItem());
		this.viewModel.setDobYear((String) this.cmbYear.getSelectedItem());

		this.viewModel.setFirstName(this.txtFirstName.getText());
		this.viewModel.setLastName(this.txtLastName.getText());

		if (this.rdbtnMale.isSelected())
			this.viewModel.setGender("Male");
		else if (this.rdbtnFemale.isSelected())
			this.viewModel.setGender("Female");
	}

}
