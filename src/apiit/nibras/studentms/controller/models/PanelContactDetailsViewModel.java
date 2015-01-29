package apiit.nibras.studentms.controller.models;

import java.util.ArrayList;
import java.util.List;

import apiit.nibras.studentms.model.types.ContactDetails;



public class PanelContactDetailsViewModel extends ViewModel {

	private String address;
	private String city;
	private String country;
	private String phone;
	private String email;

	public PanelContactDetailsViewModel() {
	}

	public PanelContactDetailsViewModel(ContactDetails contactDetails) {
		this.address = contactDetails.getStreetAddress();
		this.city = contactDetails.getCity();
		this.country = contactDetails.getCountry();
		this.phone = contactDetails.getPhone();
		this.email = contactDetails.getEmail();
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public ContactDetails toObject() {
		return new ContactDetails(this.address, this.city, this.country,
				this.phone, this.email);
	}

	@Override
	public List<String> validate() {
		List<String> list = new ArrayList<String>();

		if (this.address == null || this.address.length() == 0)
			list.add("Invalid Address");
		if (this.city == null || this.city.length() == 0)
			list.add("Invalid City");
		if (this.country == null || this.country.length() == 0)
			list.add("Invalid Country");
		if (this.phone != null && this.phone.length() != 0
				&& this.phone.matches("[A-Za-z]+"))
			list.add("Invalid Telephone Number");

		/*
			References:
			
			Mkyong.com (2009) How to validate email address with regular expression. [online] 
			Available at: http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/ 
			[Accessed: 7 Jan 2013].
			
			Stackoverflow.com (n.d.) java - validate e-mail field using regex - Stack Overflow. [online] 
			Available at: http://stackoverflow.com/questions/5788085/validate-e-mail-field-using-regex 
			[Accessed: 7 Jan 2013].
		*/
		String emailEx = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (this.email != null && this.email.length() != 0
				&& !this.email.matches(emailEx))
			list.add("Invalid EMail Address");

		return list.size() > 0 ? list : null;
	}
}
