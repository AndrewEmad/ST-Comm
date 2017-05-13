package Controllers;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DBModels.CourseDBModel;
import DBModels.GameDBModel;
import DBModels.QuestionDBModel;
import Entities.Course;
import Entities.Game;
import Entities.GameOriginator;
import Entities.Question;


@CrossOrigin(origins = "*") // allow services of this RestController to share
							// data to any client side request

@RestController
public class GameCache {

	private static Hashtable<String, Game> cache;

	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/get")
	public static Game getGame(@RequestParam String gameName) {
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
	public static boolean copyGame(@RequestParam String oldGameName, @RequestParam String newGameName, @RequestParam String sourceCourse,
								   @RequestParam String destinationCourse, @RequestParam String newTeacherName) {
		try {
			GameOriginator gameOriginator = new GameOriginator();
			gameOriginator.saveStateToGame(cache.get(oldGameName).clone());
			if(gameOriginator.getCourseName() != sourceCourse){
				gameOriginator.saveStateToGame(GameDBModel.fetchGame(oldGameName, sourceCourse));
			}
			gameOriginator.setVersion(1);
			gameOriginator.setGameName(newGameName);
			gameOriginator.setCourseName(destinationCourse);
			gameOriginator.setTeacherName(newTeacherName);
			Game game = gameOriginator.produceGame();
			GameDBModel.saveGameVersion(game);
			Vector<Question> questions = game.getQuestions();
			for (int i = 0; i < questions.size(); i++) {
				QuestionDBModel.saveQuestion(questions.get(i), newGameName,destinationCourse) ;
				
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static Vector<String> loadCache(String courseName) throws SQLException {
		Vector<String> gameNames = CourseDBModel.fetchGames(courseName);
		if(cache==null)
			cache=new Hashtable<String,Game>();
		else{
			cache.clear();
		}
		for(int i=0; i < gameNames.size(); i++){
			Game game = GameDBModel.fetchGame(gameNames.get(i), courseName);
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