package apiit.nibras.studentms.controller.models;

import java.util.ArrayList;
import java.util.List;



public class FrmLoginViewModel extends ViewModel {

	private String UserName;
	private String password;

	public String getUserName() {
		return this.UserName;
	}

	public void setUserName(String userName) {
		this.UserName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public List<String> validate() {
		ArrayList<String> list = new ArrayList<String>();

		if (this.UserName == null || this.UserName.length() == 0)
			list.add("User name cannot be empty!");
		else if (this.password == null || this.password.length() == 0)
			list.add("Password cannot be empty!");

		return list.size() > 0 ? list : null;
	}


	@Override
	public Object toObject() {
		return this;
	}

}
