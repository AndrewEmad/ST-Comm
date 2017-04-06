package Controllers;

import java.util.Date;

public class RegistrantController {

	public boolean createAccount(String name, Date birthdate, String gender, String mail,
								 String country, String password) {
		return false;
	}

	public boolean authenticate(String name, String password) {
		return false;
	}

	public boolean ValidateInput(String name, Date birthdate, String gender, String mail, String country,
			String password) {
		return false;
	}

	public void setConfirmed(String name, String activationCode) {
	}

	public void sendConfirmationMail() {
	}

}