package apiit.nibras.studentms.model.people;

import java.util.ArrayList;

import apiit.nibras.studentms.model.types.ContactDetails;
import apiit.nibras.studentms.model.types.PersonalDetails;
import apiit.nibras.studentms.model.types.Program;
import apiit.nibras.studentms.model.types.Subject;

public class Lecturer extends Member {
	private static String NEXT_ID = "lt000001";

	public Lecturer(String id) {
		super();
		this.id = id;
	}

	public Lecturer(PersonalDetails personalDetails,
			ContactDetails contactDetails) {
		super(personalDetails, contactDetails);

		this.id = NEXT_ID;
		incrementID();

		this.subjects = new ArrayList<Subject>();

	}

	public Lecturer(PersonalDetails personalDetails,
			ContactDetails contactDetails, Iterable<Subject> subjects) {
		this(personalDetails, contactDetails);

		for (Subject s : subjects)
			this.addSubject(s);
	}

	public Lecturer(PersonalDetails personalDetails,
			ContactDetails contactDetails, Program program) {
		this(personalDetails, contactDetails);
		this.program = program;
	}

	public Lecturer(PersonalDetails personalDetails,
			ContactDetails contactDetails, Program program,
			Iterable<Subject> subjects) {
		this(personalDetails, contactDetails, program);

		for (Subject c : subjects)
			this.addSubject(c);
	}

	public static String getNEXT_ID() {
		return NEXT_ID;
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
