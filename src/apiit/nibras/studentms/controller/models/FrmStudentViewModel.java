package apiit.nibras.studentms.controller.models;

import java.util.ArrayList;
import java.util.List;

import apiit.nibras.studentms.model.people.Parent;
import apiit.nibras.studentms.model.people.Student;
import apiit.nibras.studentms.model.types.EducationHistory;
import apiit.nibras.studentms.model.types.SecondaryEducationHistory;
import apiit.nibras.studentms.model.types.Subject;
import apiit.nibras.studentms.model.types.TertiaryEducationHistory;

public class FrmStudentViewModel extends ViewModel {

	private String id;

	private Student student;

	private PanelPersonalDetailsViewModel studentPDVM;
	private PanelContactDetailsViewModel studentCDVM;
	private PanelPersonalDetailsViewModel parentPDVM;
	private PanelContactDetailsViewModel parentCDVM;

	private PanelSecondaryQualificationViewModel OL_VM;
	private PanelSecondaryQualificationViewModel AL_VM;
	private PanelTertiaryQualificationViewModel UG_VM;

	private PanelEnrollmentViewModel enrollVM;

	private PanelMarksViewModel marksVM;

	private Iterable<Subject> allSubjects;

	public FrmStudentViewModel(String id, Iterable<Subject> allSubjects) {
		this.id = id;
		this.studentPDVM = new PanelPersonalDetailsViewModel();
		this.studentCDVM = new PanelContactDetailsViewModel();
		this.parentPDVM = new PanelPersonalDetailsViewModel();
		this.parentCDVM = new PanelContactDetailsViewModel();

		this.OL_VM = new PanelSecondaryQualificationViewModel("OL");
		this.AL_VM = new PanelSecondaryQualificationViewModel("AL");
		this.UG_VM = new PanelTertiaryQualificationViewModel("UG");

		this.allSubjects = allSubjects;

		this.enrollVM = new PanelEnrollmentViewModel(this.allSubjects);

	}

	public FrmStudentViewModel(Student student, Iterable<Subject> allSubjects) {
		this.student = student;
		this.id = student.getId();

		this.allSubjects = allSubjects;

		this.studentPDVM = new PanelPersonalDetailsViewModel(
				student.getPersonalDetails());
		this.studentCDVM = new PanelContactDetailsViewModel(
				student.getContactDetails());
		this.parentPDVM = new PanelPersonalDetailsViewModel(student.getParent()
				.getPersonalDetails());
		this.parentCDVM = new PanelContactDetailsViewModel(student.getParent()
				.getContactDetails());

		for (EducationHistory history : student.getAcademicHistory())
			if (history.getLevel().toString().equals("OL"))
				this.OL_VM = new PanelSecondaryQualificationViewModel(
						(SecondaryEducationHistory) history);
			else if (history.getLevel().toString().equals("AL"))
				this.AL_VM = new PanelSecondaryQualificationViewModel(
						(SecondaryEducationHistory) history);
			else if (history.getLevel().toString().equals("UG"))
				this.UG_VM = new PanelTertiaryQualificationViewModel(
						(TertiaryEducationHistory) history);

		this.OL_VM = this.OL_VM == null ? new PanelSecondaryQualificationViewModel(
				"OL") : this.OL_VM;
		this.AL_VM = this.AL_VM == null ? new PanelSecondaryQualificationViewModel(
				"AL") : this.AL_VM;
		this.UG_VM = this.UG_VM == null ? new PanelTertiaryQualificationViewModel(
				"UG") : this.UG_VM;

		if (student.getSubjects() != null && student.getSubjects().size() != 0)
			this.enrollVM = new PanelEnrollmentViewModel(allSubjects,
					student.getSubjects(), student.getProgram(),
					student.getStream());
		else
			this.enrollVM = new PanelEnrollmentViewModel(allSubjects);

		this.setMarksVM(new PanelMarksViewModel(student.getResults()));

	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PanelPersonalDetailsViewModel getStudentPDVM() {
		return this.studentPDVM;
	}

	public void setStudentPDVM(PanelPersonalDetailsViewModel studentPDVM) {
		this.studentPDVM = studentPDVM;
	}

	public PanelContactDetailsViewModel getStudentCDVM() {
		return this.studentCDVM;
	}

	public void setStudentCDVM(PanelContactDetailsViewModel studentCDVM) {
		this.studentCDVM = studentCDVM;
	}

	public PanelPersonalDetailsViewModel getParentPDVM() {
		return this.parentPDVM;
	}

	public void setParentPDVM(PanelPersonalDetailsViewModel parentPDVM) {
		this.parentPDVM = parentPDVM;
	}

	public PanelContactDetailsViewModel getParentCDVM() {
		return this.parentCDVM;
	}

	public void setParentCDVM(PanelContactDetailsViewModel parentCDVM) {
		this.parentCDVM = parentCDVM;
	}

	public PanelSecondaryQualificationViewModel getOL_VM() {
		return this.OL_VM;
	}

	public void setOL_VM(PanelSecondaryQualificationViewModel oL_VM) {
		this.OL_VM = oL_VM;
	}

	public PanelSecondaryQualificationViewModel getAL_VM() {
		return this.AL_VM;
	}

	public void setAL_VM(PanelSecondaryQualificationViewModel aL_VM) {
		this.AL_VM = aL_VM;
	}

	public PanelTertiaryQualificationViewModel getUG_VM() {
		return this.UG_VM;
	}

	public void setUG_VM(PanelTertiaryQualificationViewModel uG_VM) {
		this.UG_VM = uG_VM;
	}

	public PanelEnrollmentViewModel getEnrollVM() {
		return this.enrollVM;
	}

	public void setEnrollVM(PanelEnrollmentViewModel enrollVM) {
		this.enrollVM = enrollVM;
	}

	public PanelMarksViewModel getMarksVM() {
		return this.marksVM;
	}

	public void setMarksVM(PanelMarksViewModel marksVM) {
		this.marksVM = marksVM;
	}

	@Override
	public List<String> validate() {
		ArrayList<String> list = new ArrayList<String>();

		List<String> subList;

		subList = this.studentPDVM.validate();
		if (subList != null)
			list.addAll(subList);

		subList = this.studentCDVM.validate();
		if (subList != null)
			list.addAll(subList);

		subList = this.parentPDVM.validate();
		if (subList != null)
			list.addAll(subList);

		subList = this.parentCDVM.validate();
		if (subList != null)
			list.addAll(subList);

		subList = this.OL_VM.validate();
		if (subList != null)
			list.addAll(subList);

		subList = this.AL_VM.validate();
		if (subList != null)
			list.addAll(subList);

		subList = this.UG_VM.validate();
		if (subList != null)
			list.addAll(subList);

		subList = this.enrollVM.validate();
		if (subList != null)
			list.addAll(subList);

		return list.size() > 0 ? list : null;
	}

	@Override
	public Object toObject() {
		Student s;
		s = new Student(this.studentPDVM.toObject(),
				this.studentCDVM.toObject(), new Parent(
						this.parentPDVM.toObject(), this.parentCDVM.toObject()));

		if (!this.OL_VM.isEmpty())
			s.addAcademicHistory(this.OL_VM.toObject());

		if (!this.AL_VM.isEmpty())
			s.addAcademicHistory(this.AL_VM.toObject());

		if (!this.UG_VM.isEmpty())
			s.addAcademicHistory(this.UG_VM.toObject());

		if (this.enrollVM.getProgram() != null
				&& this.enrollVM.getStream() != null
				&& !(this.enrollVM.getSelectedSubjects().size() == 0)) {
			for (Subject sub : this.allSubjects)
				for (String subject : this.enrollVM.getSelectedSubjects())
					if (sub.toString().equals(subject))
						s.addSubject(sub);

			s.setProgram(this.enrollVM.getSelectedProgram());
			s.setStream(this.enrollVM.getSelectedStream());

		}

		return s;
	}

	public void updateObject() {
		this.student.setPersonalDetails(this.studentPDVM.toObject());
		this.student.setContactDetails(this.studentCDVM.toObject());
		this.student.setParent(new Parent(this.parentPDVM.toObject(),
				this.parentCDVM.toObject()));

		List<EducationHistory> education = new ArrayList<EducationHistory>();

		for (EducationHistory history : this.student.getAcademicHistory())
			education.add(history);

		for (EducationHistory history : education)
			this.student.removeAcademicHistory(history);

		if (!this.OL_VM.isEmpty())
			this.student.addAcademicHistory(this.OL_VM.toObject());

		if (!this.AL_VM.isEmpty())
			this.student.addAcademicHistory(this.AL_VM.toObject());

		if (!this.UG_VM.isEmpty())
			this.student.addAcademicHistory(this.UG_VM.toObject());

		List<Subject> subjects = new ArrayList<Subject>();

		for (Subject s : this.student.getSubjects())
			subjects.add(s);

		for (Subject s : subjects)
			this.student.removeSubject(s);

		if (this.enrollVM.getProgram() != null
				&& this.enrollVM.getStream() != null
				&& !(this.enrollVM.getSelectedSubjects().size() == 0)) {
			for (Subject sub : this.allSubjects)
				for (String subject : this.enrollVM.getSelectedSubjects())
					if (sub.toString().equals(subject))
						this.student.addSubject(sub);

			this.student.setProgram(this.enrollVM.getSelectedProgram());
			this.student.setStream(this.enrollVM.getSelectedStream());

		}

	}

}
