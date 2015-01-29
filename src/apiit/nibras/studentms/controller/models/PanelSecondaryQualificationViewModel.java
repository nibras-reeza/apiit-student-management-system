package apiit.nibras.studentms.controller.models;

import java.util.ArrayList;
import java.util.List;

import apiit.nibras.studentms.model.types.Level;
import apiit.nibras.studentms.model.types.SecondaryEducationHistory;
import apiit.nibras.studentms.model.types.SecondaryExamBoard;

public class PanelSecondaryQualificationViewModel extends ViewModel {

	private boolean isEmpty;
	private String level;
	private String board;
	private String school;
	private String results;
	private String stream;

	public PanelSecondaryQualificationViewModel(String level) {
		this.level = level;
		this.isEmpty = true;
	}

	public PanelSecondaryQualificationViewModel(
			SecondaryEducationHistory qualification) {
		this.isEmpty = false;
		this.level = qualification.getLevel().toString();
		this.board = qualification.getBoard().toString();
		this.school = qualification.getSchool();
		this.results = qualification.getResults();
		this.stream = qualification.getStream();
	}

	public String getBoard() {
		return this.board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getResults() {
		return this.results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String getStream() {
		return this.stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public boolean isEmpty() {
		return this.isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public String getLevel() {
		return this.level;
	}

	public SecondaryEducationHistory toObject() {
		return this.isEmpty ? null : new SecondaryEducationHistory(this.school,
				Level.valueOf(this.level), this.stream, this.results,
				SecondaryExamBoard.valueOf(this.board));
	}

	@Override
	public List<String> validate() {

		if (this.isEmpty)
			return null;

		ArrayList<String> list = new ArrayList<String>();

		if (this.board == null || this.board.length() == 0)
			list.add("Invalid examination board for " + this.level);
		if (this.school == null || this.school.length() == 0)
			list.add("Invalid school for " + this.level);
		if (this.results == null || this.results.length() == 0)
			list.add("Invalid results for " + this.level);

		return list.size() > 0 ? list : null;
	}

}
