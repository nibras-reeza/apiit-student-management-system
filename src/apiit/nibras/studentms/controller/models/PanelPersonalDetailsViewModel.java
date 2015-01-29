package apiit.nibras.studentms.controller.models;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import apiit.nibras.studentms.model.types.Gender;
import apiit.nibras.studentms.model.types.PersonalDetails;



public class PanelPersonalDetailsViewModel extends ViewModel {

	private String firstName;
	private String lastName;

	private String gender;

	private String dobDay;
	private String dobMonth;
	private String dobYear;

	public PanelPersonalDetailsViewModel() {
		super();
	}

	public PanelPersonalDetailsViewModel(PersonalDetails personalDetails) {
		this.firstName = personalDetails.getFirstName();
		this.lastName = personalDetails.getLastName();
		this.gender = personalDetails.getGender().toString();

		this.dobDay = personalDetails.getDateOfBirth().toString("d");
		this.dobMonth = personalDetails.getDateOfBirth().toString("MMMM");
		this.dobYear = personalDetails.getDateOfBirth().toString("YYYY");
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDobDay() {
		return this.dobDay;
	}

	public void setDobDay(String dobDay) {
		this.dobDay = dobDay;
	}

	public String getDobMonth() {
		return this.dobMonth;
	}

	public void setDobMonth(String dobMonth) {
		this.dobMonth = dobMonth;
	}

	public String getDobYear() {
		return this.dobYear;
	}

	public void setDobYear(String dobYear) {
		this.dobYear = dobYear;
	}


	public PersonalDetails toObject() {
		/*
			References
			
			Stackoverflow.com (2013) java - Joda time : How to convert String to LocalDate? - Stack Overflow. [online] 
			Available at: http://stackoverflow.com/questions/2721614/joda-time-how-to-convert-string-to-localdate 
			[Accessed: 7 Jan 2013].
		*/
		DateTimeFormatter parser = DateTimeFormat.forPattern("yyyyMMMMd");
		LocalDate DoB = parser.parseLocalDate(this.dobYear + this.dobMonth
				+ this.dobDay);

		return new PersonalDetails(this.firstName, this.lastName,
				Gender.valueOf(this.gender), DoB);
	}

	@Override
	public List<String> validate() {
		ArrayList<String> list = new ArrayList<String>();

		if (this.firstName == null || this.firstName.length() == 0)
			list.add("Invalid first name");
		if (this.lastName == null || this.lastName.length() == 0)
			list.add("Invalid last name");
		if (this.gender == null || this.gender.length() == 0)
			list.add("Invalid gender");

		try {
			DateTimeFormatter parser = DateTimeFormat.forPattern("yyyyMMMMd");
			parser.parseLocalDate(this.dobYear + this.dobMonth + this.dobDay);
		} catch (Exception e) {
			list.add(this.dobDay + "-" + this.dobMonth + "-" + this.dobYear
					+ " is not a valid date");
		}

		return list.size() > 0 ? list : null;
	}

}
