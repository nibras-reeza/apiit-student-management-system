package apiit.nibras.studentms.model.people;

import java.util.ArrayList;
import java.util.List;

import apiit.nibras.studentms.model.types.AssesmentResult;
import apiit.nibras.studentms.model.types.ContactDetails;
import apiit.nibras.studentms.model.types.EducationHistory;
import apiit.nibras.studentms.model.types.PersonalDetails;
import apiit.nibras.studentms.model.types.Program;
import apiit.nibras.studentms.model.types.Stream;
import apiit.nibras.studentms.model.types.Subject;



public class Student extends Member {
	private static String NEXT_ID = "cb000001";
	private Parent parent;

	private Stream stream;

	private List<AssesmentResult> results;

	// http://tobega.blogspot.com/2008/05/beautiful-enums.html

	// Constructor for creating reference key with ID.
	public Student(String id) {
		super();
		this.id = id;
	}

	/*
	 * The most basic constructor being provided. ID is automatically generated.
	 * So, am not taking in an ID. Plus, a Student that is of interest to the
	 * system must have personal and contact details. So, a default constructor
	 * is not provided.
	 */
	public Student(PersonalDetails personalDetails,
			ContactDetails contactDetails) {
		super(personalDetails, contactDetails);
		this.id = NEXT_ID;
		incrementID();
		this.subjects = new ArrayList<Subject>();
		this.results = new ArrayList<AssesmentResult>();
		this.academicHistory = new ArrayList<EducationHistory>();
	}

	public Student(PersonalDetails personalDetails,
			ContactDetails contactDetails, Parent parent) {
		this(personalDetails, contactDetails);
		this.parent = parent;
	}

	public Student(PersonalDetails personalDetails,
			ContactDetails contactDetails, Parent parent, Program program) {
		this(personalDetails, contactDetails);
		this.parent = parent;
		this.program = program;
	}

	public Student(PersonalDetails personalDetails,
			ContactDetails contactDetails, Parent parent, Program program,
			Stream stream) {
		this(personalDetails, contactDetails);
		this.parent = parent;
		this.program = program;
		this.stream = stream;
	}

	public Student(PersonalDetails personalDetails,
			ContactDetails contactDetails, Parent parent, Program program,
			Stream stream, Iterable<Subject> subjects) {
		this(personalDetails, contactDetails);
		this.parent = parent;
		this.program = program;
		this.stream = stream;

		for (Subject s : subjects)
			this.addSubject(s);
	}

	public Student(PersonalDetails personalDetails,
			ContactDetails contactDetails, Parent parent, Program program,
			Stream stream, Iterable<Subject> subjects,
			Iterable<AssesmentResult> results) {
		this(personalDetails, contactDetails, parent, program, stream, subjects);

		for (AssesmentResult result : results)
			this.addResult(result);
	}

	public Student(PersonalDetails personalDetails,
			ContactDetails contactDetails, Parent parent, Program program,
			Stream stream, Iterable<Subject> subjects,
			Iterable<AssesmentResult> results,
			Iterable<EducationHistory> academicHistory) {
		this(personalDetails, contactDetails, parent, program, stream,
				subjects, results);

		for (EducationHistory educationHistory : academicHistory)
			this.addAcademicHistory(educationHistory);
	}

	public Student(PersonalDetails personalDetails,
			ContactDetails contactDetails, Program program, Stream stream,
			Iterable<Subject> subjects) {
		this(personalDetails, contactDetails);
		this.program = program;
		this.stream = stream;

		for (Subject s : subjects)
			this.addSubject(s);
	}

	public Student(PersonalDetails personalDetails,
			ContactDetails contactDetails, Program program, Stream stream,
			Iterable<Subject> subjects, Iterable<AssesmentResult> results) {
		this(personalDetails, contactDetails, program, stream, subjects);

		for (AssesmentResult result : results)
			this.addResult(result);
	}

	public Student(PersonalDetails personalDetails,
			ContactDetails contactDetails, Program program, Stream stream,
			Iterable<Subject> subjects, Iterable<AssesmentResult> results,
			Iterable<EducationHistory> academicHistory) {
		this(personalDetails, contactDetails, program, stream, subjects,
				results);

		for (EducationHistory educationHistory : academicHistory)
			this.addAcademicHistory(educationHistory);
	}

	public Parent getParent() {
		return this.parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public Stream getStream() {
		return this.stream;
	}

	public void setStream(Stream stream) {
		this.stream = stream;
	}

	public void addResult(AssesmentResult assesmentResult) {
		this.results.add(assesmentResult);
	}

	public boolean removeResult(AssesmentResult assesmentResult) {
		return this.results.remove(assesmentResult);
	}

	public List<AssesmentResult> getResults() {
		return this.results;
	}

	public static String getNEXT_ID() {
		return NEXT_ID;
	}


	public String toString() {
		return "Student:\n" + super.toString() + "\nParent:\n"
				+ this.parent.toString() + this.academicHistory;
	}

	private static void incrementID() {
		/*
			References
			Docs.oracle.com (2006) Pattern (Java Platform SE 6). [online] Available at: http://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html#sum [Accessed: 7 Jan 2013].

			Docs.oracle.com (n.d.) String (Java Platform SE 6). [online] Available at: http://docs.oracle.com/javase/6/docs/api/java/lang/String.html#split%28java.lang.String%29 [Accessed: 7 Jan 2013].

			Docs.oracle.com (1995) Formatter (Java 2 Platform SE 5.0). [online] Available at: http://docs.oracle.com/javase/1.5.0/docs/api/java/util/Formatter.html#syntax [Accessed: 7 Jan 2013].

			Stackoverflow.com (n.d.) Add leading zeroes to number in Java? - Stack Overflow. [online] Available at: http://stackoverflow.com/questions/275711/add-leading-zeroes-to-number-in-java [Accessed: 7 Jan 2013].
					
		*/
		int ID = Integer.parseInt(NEXT_ID.split("[a-zA-Z]+")[1]);
		ID++;
		NEXT_ID = String.format("%s%06d", NEXT_ID.split("[0-9]")[0], ID);
	}

}
