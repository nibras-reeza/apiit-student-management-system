package apiit.nibras.studentms.model.types;

public class AssesmentResult {
	private Subject subject;
	private double score;
	private Grade grade;

	public AssesmentResult(Subject subject, double score) {
		super();
		this.subject = subject;
		this.score = score;
		this.grade = Grade.create(score);
	}

	public Subject getsubject() {
		return this.subject;
	}

	public void setsubject(Subject subject) {
		this.subject = subject;
	}

	public double getScore() {
		return this.score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public String toString() {
		return "Grade obtained for the class,\n" + this.getsubject() + "\n"
				+ this.getGrade() + "(" + this.getScore() + ")";
	}
}
