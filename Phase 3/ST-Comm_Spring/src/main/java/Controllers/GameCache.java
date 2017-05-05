package Controllers;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import DBModels.CourseDBModel;
import DBModels.GameDBModel;
import Entities.Game;
import Entities.GameOriginator;


@CrossOrigin(origins = "*") // allow services of this RestController to share
							// data to any client side request

@RestController
public class GameCache {

	private static Hashtable<String, Game> cache;

	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/get")
	public static Game getGame(String gameName) {
		try {
			GameOriginator gameOriginator = new GameOriginator();
			gameOriginator.saveStateToGame(cache.get(gameName).clone());
			return gameOriginator.produceGame();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/copy")
	public static Game copyGame(String gameName) {
		try {
			GameOriginator gameOriginator = new GameOriginator();
			gameOriginator.saveStateToGame(cache.get(gameName).clone());
			gameOriginator.setVersion(1);
			return gameOriginator.produceGame();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Vector<String> loadCache(String courseName) throws SQLException {
		Vector<String> gameNames = CourseDBModel.fetchGames(courseName);
		cache.clear();
		for(int i=0; i < gameNames.size(); i++){
			Game game = GameDBModel.fetchGame(gameNames.get(i));
			cache.put(gameNames.get(i), game);
		}
		return gameNames;
	}
	
	public static void addToCache(Game game){
		cache.put(game.getName(), game);
	}
	
	public static void removeFromCache(String gameName){
		cache.remove(gameName);
	}
}