package apiit.nibras.studentms.controller.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import apiit.nibras.studentms.model.types.Program;
import apiit.nibras.studentms.model.types.Subject;

public class PanelEnlistViewModel extends ViewModel {

	private String program;

	private List<Subject> allSubjects;
	private List<String> selectedSubjects;
	private DefaultComboBoxModel<String> programs;
	private DefaultComboBoxModel<String> subjects;

	public PanelEnlistViewModel(Iterable<Subject> allSubjects) {
		this.programs = new DefaultComboBoxModel<String>();
		this.subjects = new DefaultComboBoxModel<String>();

		this.allSubjects = new ArrayList<Subject>();

		this.selectedSubjects = new ArrayList<String>();

		for (Program p : Program.values())
			this.programs.addElement(p.toString());

		for (Subject c : allSubjects)
			this.allSubjects.add(c);
	}

	public PanelEnlistViewModel(Iterable<Subject> allSubjects,
			Iterable<Subject> selectedSubjects, Program program) {
		this(allSubjects);

		this.setProgram(program.toString());

		for (Subject s : selectedSubjects)
			this.selectedSubjects.add(s.toString());

	}

	public String getProgram() {
		return this.program;
	}

	public void setProgram(String program) {
		if (this.program == null || !this.program.equals(program)) {
			this.program = program;

			this.subjects.removeAllElements();
			for (Subject c : this.allSubjects)
				if (c.getProgram() == Program.valueOf(this.program))
					this.subjects.addElement(c.toString());
		}
	}

	public List<String> getSelectedSubjects() {
		return this.selectedSubjects;
	}

	public void setSelectedSubjects(Iterable<String> selectedSubjects) {
		this.selectedSubjects.clear();
		for (String s : selectedSubjects)
			this.selectedSubjects.add(s);
	}

	public ComboBoxModel<String> getPrograms() {
		return this.programs;
	}

	public DefaultComboBoxModel<String> getSubjects() {
		return this.subjects;
	}

	@Override
	public List<String> validate() {
		List<String> list = new ArrayList<String>();

		if (this.selectedSubjects.size() > 3)
			list.add("Too many subjects");

		return list.size() > 0 ? list : null;
	}

	@Override
	public Object toObject() {
		return this.selectedSubjects;
	}

	public Program getSelectedProgram() {
		return Program.valueOf(this.program);
	}

}
