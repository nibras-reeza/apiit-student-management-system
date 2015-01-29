package apiit.nibras.studentms.controller.models;

import java.util.List;

import apiit.nibras.studentms.model.types.AssesmentResult;

public class PanelMarksViewModel extends ViewModel {

	private String report = "Progress:\n";

	public String getReport() {
		return this.report == null ? "\n" : this.report;
	}

	PanelMarksViewModel(Iterable<AssesmentResult> results) {
		for (AssesmentResult result : results) {
			this.report += String.format("%s - %s: %s\n", result.getsubject()
					.getCode(), result.getsubject().getName(), result
					.getGrade().toString());
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
