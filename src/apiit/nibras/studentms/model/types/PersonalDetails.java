package apiit.nibras.studentms.model.types;

import org.joda.time.LocalDate;

public class PersonalDetails {
	private String firstName;
	private String lastName;

	private Gender gender;

	private LocalDate dateOfBirth;

	public PersonalDetails(String firstName, String lastName, Gender gender,
			LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}

	public PersonalDetails(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String toString() {
		return this.getFirstName() + " " + this.getLastName() + "\n"
				+ "Gender: " + this.getGender() + "\n" + "Date of Birth: "
				+ this.dateOfBirth;
	}

	public static void main(String... strings) {
		LocalDate DoB = new LocalDate(1990, 8, 8);
		PersonalDetails pd = new PersonalDetails("Nibras", "Reeza",
				Gender.Male, DoB);
		System.out.println(pd);
	}

}
