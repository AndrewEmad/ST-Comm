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
	public boolean createGame(@RequestParam String gameName, @RequestParam Vector<Question> questions,
			@RequestParam String teacherName) {
		Game game = new Game();
		game.setInfo(gameName, questions, teacherName);
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

	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/scores/save")
	public boolean saveScore(@RequestParam String name, @RequestParam int score, @RequestParam String gameName) {
		try {
			GameDBModel.saveScore(name, score, gameName);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

}