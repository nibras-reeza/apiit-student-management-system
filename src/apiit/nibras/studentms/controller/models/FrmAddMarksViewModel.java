package apiit.nibras.studentms.controller.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import apiit.nibras.studentms.model.people.Lecturer;
import apiit.nibras.studentms.model.people.Student;
import apiit.nibras.studentms.model.types.AssesmentResult;
import apiit.nibras.studentms.model.types.Subject;

public class FrmAddMarksViewModel extends ViewModel {

	private Lecturer lecturer;

	private String subject;

	private DefaultComboBoxModel<String> subjects;
	private ArrayList<String> students;

	public FrmAddMarksViewModel(Lecturer lecturer) {
		this.lecturer = lecturer;

		this.subjects = new DefaultComboBoxModel<String>();
		this.students = new ArrayList<String>();

		for (Subject s : lecturer.getSubjects())
			this.subjects.addElement(s.toString());

	}

	public void setSubject(String subject) {
		if (!subject.equals(this.subject)) {
			this.subject = subject;
			this.students.clear();
			for (Subject s : this.lecturer.getSubjects())
				if (s.toString().equals(subject))
					for (Student student : s.getStudents())
						this.students.add(student.getId());
		}

	}

	public DefaultComboBoxModel<String> getSubjects() {
		return this.subjects;
	}

	public ArrayList<String> getStudents() {
		return this.students;
	}

	public void setScore(String studentID, double score) {
		for (Subject s : this.lecturer.getSubjects())
			if (s.toString().equals(this.subject)) {
				for (Student student : s.getStudents())
					if (student.getId().equals(studentID))
						student.addResult(new AssesmentResult(s, score));
			}
	}

	@Override
	public List<String> validate() {
		return null;
	}

	@Override
	public Object toObject() {
		return null;
	}

}
