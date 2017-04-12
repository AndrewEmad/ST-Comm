package DBModels;

import java.sql.SQLException;

import Entities.Registrant;

public class StudentDBModel extends RegistrantDBModel {

	public static boolean saveScore(String name, int score, String gameName) {
		return false;
	}
}