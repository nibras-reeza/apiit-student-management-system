package apiit.nibras.studentms.controller.models;

import java.util.List;

import apiit.nibras.studentms.model.people.Student;

public class FrmStudentsViewModel extends ViewModel {

	private TableStudentsModel tableModel;
	private String id;

	public FrmStudentsViewModel(Iterable<Student> students) {
		this.tableModel = new TableStudentsModel(students);

	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public List<String> validate() {
		// No validation supported yet.
		return null;
	}

	@Override
	public Object toObject() {
		return this;
	}

	public TableStudentsModel getTableModel() {
		return this.tableModel;

	}

}
