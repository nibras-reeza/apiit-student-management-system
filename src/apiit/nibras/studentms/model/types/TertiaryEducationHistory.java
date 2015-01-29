package apiit.nibras.studentms.model.types;


public class TertiaryEducationHistory extends EducationHistory {
	private String university;
	private String degree;
	private double GPA;

	public TertiaryEducationHistory(String university, Level level,
			String degree, double gPA) {
		super();
		this.university = university;
		this.level = level;
		this.degree = degree;
		this.GPA = gPA;
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

	public double getGPA() {
		return this.GPA;
	}

	public void setGPA(double gPA) {
		this.GPA = gPA;
	}

	public String toString() {
		return "University: " + this.getUniversity() + "\nLevel: "
				+ this.getLevel() + "\nDegree: " + this.getDegree() + "\nGPA: "
				+ this.getGPA();
	}

	public static void main(String... strings) {
		TertiaryEducationHistory tertiaryEducationHistory = new TertiaryEducationHistory(
				"Staffordshire University", Level.UG,
				"BSc. Information Technology", 4.5);
		System.out.println(tertiaryEducationHistory);
	}
}
