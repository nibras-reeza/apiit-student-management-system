package apiit.nibras.studentms.controller.models;

import java.util.ArrayList;
import java.util.List;

import apiit.nibras.studentms.model.types.Level;
import apiit.nibras.studentms.model.types.TertiaryEducationHistory;

public class PanelTertiaryQualificationViewModel extends ViewModel {

	private boolean isEmpty;
	private String level;
	private String university;
	private String degree;
	private String gpa;

	public PanelTertiaryQualificationViewModel(String level) {
		this.level = level;
		this.isEmpty = true;
	}

	public PanelTertiaryQualificationViewModel(
			TertiaryEducationHistory qualification) {
		this.level = qualification.getLevel().toString();
		this.university = qualification.getUniversity();
		this.degree = qualification.getDegree();
		this.gpa = Double.toString(qualification.getGPA());
		this.isEmpty = false;
	}

	public String getLevel() {
		return this.level;
	}

	public String getUniversity() {
		return this.university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getGpa() {
		return this.gpa;
	}

	public void setGpa(String gpa) {
		this.gpa = gpa;
	}

	public boolean isEmpty() {
		return this.isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public TertiaryEducationHistory toObject() {
		return this.isEmpty ? null : new TertiaryEducationHistory(
				this.university, Level.valueOf(this.level), this.degree,
				Double.parseDouble(this.gpa));
	}

	@Override
	public List<String> validate() {

		if (this.isEmpty)
			return null;

		ArrayList<String> list = new ArrayList<String>();

		if (this.university == null || this.university.length() == 0)
			list.add("Invalid degree awarding institution for " + this.level);

		if (this.degree == null || this.degree.length() == 0)
			list.add("Invalid degree for " + this.level);

		if (this.gpa == null || this.gpa.length() == 0
				|| this.gpa.matches("[A-Za-z]+"))
			list.add("Invalid GPA for " + this.level);

		return list.size() > 0 ? list : null;
	}

}
