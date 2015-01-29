package apiit.nibras.studentms.model.types;


public class ContactDetails {
	private String streetAddress;
	private String city;
	private String country;

	private String phone;
	private String email;

	public ContactDetails(String streetAddress, String city, String country) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.country = country;
	}

	public ContactDetails(String streetAddress, String city, String country,
			String phone) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.country = country;
		this.phone = phone;
	}

	public ContactDetails(String streetAddress, String city, String country,
			String phone, String email) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.email = email;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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

	@Override
	public String toString() {
		return (this.streetAddress != null ? this.streetAddress + "\n" : "")
				+ (this.city != null ? this.city + "\n" : "")
				+ (this.country != null ? this.country + "\n" : "")
				+ (this.phone != null ? this.phone + "\n" : "")
				+ (this.email != null ? this.email : "\n");
	}

	public static void main(String... strings) {
		ContactDetails cd = new ContactDetails("16/A-1, Vijaya Road,\n",
				"Kolonnawa", "Sri Lanka", "+94779966375", "nibras@facebook.me");
		System.out.println(cd);
	}

}