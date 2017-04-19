package Controllers;

import java.sql.SQLException;
import java.util.Vector;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DBModels.GameDBModel;
import DBModels.QuestionDBModel;
import Entities.Game;
import Entities.Question;

@CrossOrigin(origins = "*") // allow services of this RestController to share
							// data to
// any client side request
@RestController
public class GameController {

	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/play")
	public Game playGame(@RequestParam String gameName) {
		Game game = null;
		try {
			game = GameDBModel.fetchGame(gameName);
			Vector<Question> questions = QuestionDBModel.fetchQuestions(gameName);
			game.setQuestions(questions);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return game;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/new")
	public boolean createGame(@RequestParam String gameName, @RequestParam String courseName,
							  @RequestParam String teacherName, @RequestParam Vector<Question> questions) {
		Game game = new Game();
		game.setInfo(gameName, courseName, teacherName, questions);
		try {
			if (GameDBModel.saveGame(game) == false) {
				return false;
			}
			for (int i = 0; i < questions.size(); i++) {
				if (QuestionDBModel.saveQuestion(questions.get(i), gameName) == false) {
					return false;
				}
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/scores/save")
	public boolean saveScore(@RequestParam String name, @RequestParam int score, @RequestParam String gameName) {
		try {
			return GameDBModel.saveScore(name, score, gameName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/exists")
	public boolean exists(@RequestParam String gameName){
		return GameDBModel.exists(gameName);
	}
}