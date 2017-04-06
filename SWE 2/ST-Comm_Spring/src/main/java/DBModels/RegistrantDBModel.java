package DBModels;

import Entities.Registrant;

public class RegistrantDBModel {

	public boolean authenticate(String name, String password) {
		return false;
	}

	public boolean exists(String name, String mail) {
		return false;
	}

	public void setConfirmed(String name, String activationCode) {
	}

	public boolean saveAccount(Registrant registrant) {
		return false;
	}

}