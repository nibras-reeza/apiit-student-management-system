package apiit.nibras.studentms.controller.models;

import java.util.ArrayList;
import java.util.List;

import apiit.nibras.studentms.model.people.Lecturer;
import apiit.nibras.studentms.model.types.EducationHistory;
import apiit.nibras.studentms.model.types.SecondaryEducationHistory;
import apiit.nibras.studentms.model.types.Subject;
import apiit.nibras.studentms.model.types.TertiaryEducationHistory;

public class FrmLecturerViewModel extends ViewModel {
	private String id;

	private Lecturer lecturer;

	private PanelPersonalDetailsViewModel lecturerPDVM;
	private PanelContactDetailsViewModel lecturerCDVM;

	private PanelSecondaryQualificationViewModel OL_VM;
	private PanelSecondaryQualificationViewModel AL_VM;
	private PanelTertiaryQualificationViewModel UG_VM;

	private PanelEnlistViewModel enlistVM;

	private Iterable<Subject> allSubjects;

	public FrmLecturerViewModel(String id, Iterable<Subject> allSubjects) {
		this.id = id;
		this.lecturerPDVM = new PanelPersonalDetailsViewModel();
		this.lecturerCDVM = new PanelContactDetailsViewModel();

		this.OL_VM = new PanelSecondaryQualificationViewModel("OL");
		this.AL_VM = new PanelSecondaryQualificationViewModel("AL");
		this.UG_VM = new PanelTertiaryQualificationViewModel("UG");

		this.allSubjects = allSubjects;
		this.enlistVM = new PanelEnlistViewModel(this.allSubjects);

	}

	public FrmLecturerViewModel(Lecturer lecturer, Iterable<Subject> allSubjects) {
		this.lecturer = lecturer;
		this.id = lecturer.getId();
		this.allSubjects = allSubjects;

		this.lecturerPDVM = new PanelPersonalDetailsViewModel(
				lecturer.getPersonalDetails());
		this.lecturerCDVM = new PanelContactDetailsViewModel(
				lecturer.getContactDetails());

		for (EducationHistory history : lecturer.getAcademicHistory())
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

		if (lecturer.getSubjects() != null
				&& lecturer.getSubjects().size() != 0)
			this.enlistVM = new PanelEnlistViewModel(allSubjects,
					lecturer.getSubjects(), lecturer.getProgram());
		else
			this.enlistVM = new PanelEnlistViewModel(allSubjects);

	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PanelPersonalDetailsViewModel getLecturerPDVM() {
		return this.lecturerPDVM;
	}

	public void setLecturerPDVM(PanelPersonalDetailsViewModel lecturerPDVM) {
		this.lecturerPDVM = lecturerPDVM;
	}

	public PanelContactDetailsViewModel getLecturerCDVM() {
		return this.lecturerCDVM;
	}

	public void setLecturerCDVM(PanelContactDetailsViewModel lecturerCDVM) {
		this.lecturerCDVM = lecturerCDVM;
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

	public PanelEnlistViewModel getEnlistVM() {
		return this.enlistVM;
	}

	public void setEnlistVM(PanelEnlistViewModel enrollVM) {
		this.enlistVM = enrollVM;
	}

	@Override
	public List<String> validate() {
		ArrayList<String> list = new ArrayList<String>();

		List<String> subList;

		subList = this.lecturerPDVM.validate();
		if (subList != null)
			list.addAll(subList);

		subList = this.lecturerCDVM.validate();
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

		subList = this.enlistVM.validate();
		if (subList != null)
			list.addAll(subList);

		return list.size() > 0 ? list : null;
	}

	@Override
	public Object toObject() {
		Lecturer s;
		s = new Lecturer(this.lecturerPDVM.toObject(),
				this.lecturerCDVM.toObject());

		if (!this.OL_VM.isEmpty())
			s.addAcademicHistory(this.OL_VM.toObject());

		if (!this.AL_VM.isEmpty())
			s.addAcademicHistory(this.AL_VM.toObject());

		if (!this.UG_VM.isEmpty())
			s.addAcademicHistory(this.UG_VM.toObject());

		if (this.enlistVM.getProgram() != null
				&& !(this.enlistVM.getSelectedSubjects().size() == 0)) {
			for (Subject sub : this.allSubjects)
				for (String subject : this.enlistVM.getSelectedSubjects())
					if (sub.toString().equals(subject))
						s.addSubject(sub);

			s.setProgram(this.enlistVM.getSelectedProgram());

		}

		return s;
	}

	public void updateObject() {
		this.lecturer.setPersonalDetails(this.lecturerPDVM.toObject());
		this.lecturer.setContactDetails(this.lecturerCDVM.toObject());

		List<EducationHistory> education = new ArrayList<EducationHistory>();

		for (EducationHistory history : this.lecturer.getAcademicHistory())
			education.add(history);

		for (EducationHistory history : education)
			this.lecturer.removeAcademicHistory(history);

		if (!this.OL_VM.isEmpty())
			this.lecturer.addAcademicHistory(this.OL_VM.toObject());

		if (!this.AL_VM.isEmpty())
			this.lecturer.addAcademicHistory(this.AL_VM.toObject());

		if (!this.UG_VM.isEmpty())
			this.lecturer.addAcademicHistory(this.UG_VM.toObject());

		List<Subject> subjects = new ArrayList<Subject>();

		for (Subject s : this.lecturer.getSubjects())
			subjects.add(s);

		for (Subject s : subjects)
			this.lecturer.removeSubject(s);

		if (this.enlistVM.getProgram() != null
				&& !(this.enlistVM.getSelectedSubjects().size() == 0)) {
			for (Subject sub : this.allSubjects)
				for (String subject : this.enlistVM.getSelectedSubjects())
					if (sub.toString().equals(subject))
						this.lecturer.addSubject(sub);

			this.lecturer.setProgram(this.enlistVM.getSelectedProgram());

		}

	}
}
