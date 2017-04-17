package DBModels;

import Entities.Game;

public class GameDBModel {

	public static boolean saveGame(Game game) {
		return true;
	}

	public static Game fetchGame(String gameName) {
		return new Game();
	}

	public static boolean saveScore(String name, int score, String gameName) {
		return true;
	}
}