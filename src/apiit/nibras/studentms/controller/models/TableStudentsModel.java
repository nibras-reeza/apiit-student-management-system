package apiit.nibras.studentms.controller.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import apiit.nibras.studentms.model.people.Student;
import apiit.nibras.studentms.model.types.AssesmentResult;

public class TableStudentsModel extends AbstractTableModel {


	private static final long serialVersionUID = -4147439633794563739L;
	private List<Student> students = new ArrayList<Student>();

	public TableStudentsModel(Iterable<Student> students) {
		for (Student s : students)
			this.students.add(s);

	}

	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "ID";
		case 1:
			return "First Name";
		case 2:
			return "Last Name";
		case 3:
			return "Date of Birth";
		case 4:
			return "Subject 1";
		case 5:
			return "Subject 1 Marks";
		case 6:
			return "Subject 2";
		case 7:
			return "Subject 2 Marks";
		case 8:
			return "Subject 3";
		case 9:
			return "Subject 3 Marks";
		case 10:
			return "Average";
		case 11:
			return "Address";
		case 12:
			return "Phone";
		case 13:
			return "EMail";

		default:
			return null;
		}
	}

	@Override
	public int getColumnCount() {
		return 14;
	}

	@Override
	public int getRowCount() {
		return this.students.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Student s = this.students.get(row);

		switch (col) {
		case 0:
			return s.getId();
		case 1:
			return s.getPersonalDetails().getFirstName();
		case 2:
			return s.getPersonalDetails().getLastName();
		case 3:
			return s.getPersonalDetails().getDateOfBirth();
		case 4:
			return s.getResults().size() > 0 ? s.getResults().get(0)
					.getsubject().getName() : null;
		case 5:
			return s.getResults().size() > 0 ? s.getResults().get(0).getScore()
					: null;
		case 6:
			return s.getResults().size() > 1 ? s.getResults().get(1)
					.getsubject().getName() : null;
		case 7:
			return s.getResults().size() > 1 ? s.getResults().get(1).getScore()
					: null;
		case 8:
			return s.getResults().size() > 2 ? s.getResults().get(2)
					.getsubject().getName() : null;
		case 9:
			return s.getResults().size() > 2 ? s.getResults().get(2).getScore()
					: null;
		case 10:
			int x = -1;
			for (AssesmentResult result : s.getResults())
				x += result.getScore();
			return x == -1 ? null : (x + 1) / s.getResults().size();
		case 11:
			return s.getContactDetails().getStreetAddress() + " "
					+ s.getContactDetails().getCity() + " "
					+ s.getContactDetails().getCountry();
		case 12:
			return s.getContactDetails().getPhone();
		case 13:
			return s.getContactDetails().getEmail();

		}
        return null;
	}

}
