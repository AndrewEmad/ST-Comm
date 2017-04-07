package DBModels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import Entities.Registrant;

public class RegistrantDBModel {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public static boolean authenticate(String name, String password) {
		return true;
	}

	public static boolean exists(String name, String mail) {
		return false;
	}

	public static boolean setConfirmed(String name, String activationCode) {
		return true;
	}

	public static boolean saveAccount(Registrant registrant) {
		return true;
	}

	public static String getActivationCode(String name){
		return "DummyActivationCode";
	}
}