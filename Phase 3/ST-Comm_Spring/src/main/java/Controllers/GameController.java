package Controllers;

import java.sql.SQLException;
import java.util.Vector;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DBModels.GameDBModel;
import DBModels.QuestionDBModel;
import Entities.Game;
import Entities.Question;
import Entities.QuestionJSONWrapper;

@CrossOrigin(origins = "*") // allow services of this RestController to share
							// data to any client side request
/**
 * 
 * @author Ahmed Hussein
 *
 */
@RestController
public class GameController {

	/**
	 * 
	 * @param binder binds an object form the {@link Entities.QuestionJSONWrapper} class 
	 * 		  according to the JSON data delivered to the service
	 * 		  {@link Controllers.GameController#createGame(String, String, String, QuestionJSONWrapper)}}
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(QuestionJSONWrapper.class, new QuestionJSONWrapper());
	}
	
	/**
	 * 
	 * @param gameName: the game to be played
	 * @return and object of the class {@link Entities.Game} instantiated from the database
	 * 		   models
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/play")
	public Game playGame(@RequestParam String gameName) {
		Game game = null;
		try {
			game = GameDBModel.fetchGame(gameName);
			Vector<Question> questions = QuestionDBModel.fetchQuestions(gameName);
			game.setQuestions(questions);
			game.setNumOfQuestions(questions.size());
		} catch (SQLException e) {
			return null;
		}
		return game;
	}

	/**
	 * 
	 * @param gameName: the name of the game to be created
	 * @param courseName: the course in which the game will be saved
	 * @param teacherName: the creator of the course
	 * @param wrapper: an object that wraps up the questions of the game
	 * @return true if the game was created successfully, and false otherwise
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/new")
	public boolean createGame(@RequestParam String gameName, @RequestParam String courseName,
			  @RequestParam String teacherName, @RequestParam QuestionJSONWrapper wrapper) {
		Game game = new Game();
		Vector<Question> questions = wrapper.getQuestions();
		game.setInfo(gameName, courseName, teacherName, questions);
		game.setNumOfQuestions(questions.size());
		try {
			GameDBModel.saveGame(game);
			for (int i = 0; i < questions.size(); i++) {
				QuestionDBModel.saveQuestion(questions.get(i), gameName) ;
				
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param name: the name of the registrant for whom the score will be saved
	 * @param score
	 * @param gameName
	 * @return true if the scored was saved successfully, and false otherwise
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/scores/save")
	public boolean saveScore(@RequestParam String name, @RequestParam int score,
							 @RequestParam String gameName) {
		try {
			GameDBModel.saveScore(name, score, gameName);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param gameName: the game being checked whether or not it exists in the system
	 * @return true if the game exists, and false otherwise
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/exists")
	public boolean exists(@RequestParam String gameName){
		try {
			GameDBModel.exists(gameName);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
}