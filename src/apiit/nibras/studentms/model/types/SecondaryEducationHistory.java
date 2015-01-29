package apiit.nibras.studentms.model.types;


public class SecondaryEducationHistory extends EducationHistory {
	private String school;
	private String stream;
	private String results;
	private SecondaryExamBoard board;

	public SecondaryEducationHistory(String school, Level level, String stream,
			String results, SecondaryExamBoard board) {
		super();
		this.school = school;
		this.level = level;
		this.stream = stream;
		this.results = results;
		this.board = board;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getStream() {
		return this.stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getResults() {
		return this.results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public SecondaryExamBoard getBoard() {
		return this.board;
	}

	public void setBoard(SecondaryExamBoard board) {
		this.board = board;
	}

	public String toString() {
		return this.getBoard() + " " + this.getLevel() + "s ("
				+ this.getStream() + ")\n" + "Results: " + this.getResults()
				+ "\n" + "School: " + this.getSchool() + ".";
	}

	public static void main(String... strings) {
		SecondaryEducationHistory seh = new SecondaryEducationHistory(
				"Royal Institute of Colombo", Level.AL, "Biological Science",
				"B,B,C", SecondaryExamBoard.Local);
		System.out.println(seh);
	}
}
