package Controllers;

import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DBModels.GameDBModel;
import DBModels.LogDBModel;
import DBModels.QuestionDBModel;
import DBModels.RegistrantDBModel;
import Entities.Course;
import Entities.Game;
import Entities.GameOriginator;
import Entities.LogMessage;
import Entities.LogOperation;
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
	public Game playGame(@RequestParam String gameName, @RequestParam String courseName) {
		Game game = null;
		try {
			GameOriginator gameOriginator = new GameOriginator();
			gameOriginator.saveStateToGame(GameDBModel.fetchGame(gameName, courseName));
			Vector<Question> questions = QuestionDBModel.fetchQuestions(gameName, courseName);
			gameOriginator.setQuestions(questions);
			gameOriginator.setNumOfQuestions(questions.size());
			game = gameOriginator.produceGame();
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
		try {
			if(saveGame(gameName, courseName, teacherName, wrapper, 1) == false){
				return false;
			}
			Vector<Question> questions = wrapper.getQuestions();
			for (int i = 0; i < questions.size(); i++) {
				QuestionDBModel.saveQuestion(questions.get(i), gameName,courseName) ;
				
			}
			Course course = new Course(courseName);
			course.notifyAllObservers(gameName);
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
							 @RequestParam String gameName, @RequestParam String courseName) {
		try {
			GameDBModel.saveScore(name, score, gameName, courseName);
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
	public boolean exists(@RequestParam String gameName, @RequestParam String courseName){
		try {
			GameDBModel.exists(gameName, courseName);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static boolean saveGame(String gameName, String courseName, String teacherName,
								   QuestionJSONWrapper wrapper, int version){
		GameOriginator gameOriginator = new GameOriginator();
		Vector<Question> questions = wrapper.getQuestions();
		gameOriginator.setInfo(gameName, courseName, questions.size(), teacherName, questions, false, version);
		Game game = gameOriginator.produceGame();
		GameCache.addToCache(game);
		try {
			GameDBModel.saveGameVersion(game);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/cancel")
	public static boolean cancelGame(@RequestParam String gameName, @RequestParam String courseName,
									 @RequestParam String teacherName){
		if(isCollaborator(teacherName, gameName, courseName) == false){
			return false;
		}
		GameCache.removeFromCache(gameName);
		try {
			GameDBModel.cancelGame(gameName, courseName);
			LogDBModel.writeMessage(new LogMessage(LogOperation.CANCEL.getValue(), gameName, gameName, teacherName, courseName, new Date()));
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/uncancel")
	public static boolean uncancelGame(@RequestParam String gameName, @RequestParam String courseName,
									   @RequestParam String teacherName){
		if(isCollaborator(teacherName, gameName, courseName) == false){
			return false;
		}
		try {
			GameDBModel.uncancelGame(gameName, courseName);
			LogDBModel.writeMessage(new LogMessage(LogOperation.UNCANCEL.getValue(), gameName, gameName, teacherName, courseName, new Date()));
		} catch (SQLException e) {
			return false;
		}
		try {
			Game game;
			game = GameDBModel.fetchGame(gameName, courseName);
			GameCache.addToCache(game);
		} catch (SQLException e) {
			e.printStackTrace(); // do not return false because the game has been cancelled above
		}
		return true;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/edit")
	public static boolean replaceOnEdit(@RequestParam String newGameName, @RequestParam String oldGameName,
										@RequestParam String courseName, @RequestParam String teacherName,
										@RequestParam QuestionJSONWrapper wrapper, @RequestParam int version){
		if(isCollaborator(teacherName, oldGameName, courseName) == false){
			return false;
		}
		version++;
		cancelGame(oldGameName, courseName, teacherName);
		saveGame(newGameName, courseName, teacherName, wrapper, version);
		try {
			LogDBModel.writeMessage(new LogMessage(LogOperation.EDIT.getValue(), oldGameName, newGameName, teacherName, courseName, new Date()));
		} catch (SQLException e) { // does not return false because the edit has been committed
			e.printStackTrace();
		}
		return true;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/comments/new")
	public boolean addComment(@RequestParam String gameName, @RequestParam String courseName,
							  @RequestParam String comment, @RequestParam String registrantName){
		String message;
		message = "New comment on game \"" + gameName + "\" by \"" + registrantName + "\"";
		try {
			GameDBModel.saveComment(gameName, courseName, comment);
		} catch (SQLException e) {
			return false;
		}
		RegistrantDBModel.pushNotification(message, registrantName);
		return true;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/query/comments")
	public Vector<String> getComments(@RequestParam String gameName, @RequestParam String courseName){
		try {
			return GameDBModel.fetchComments(gameName, courseName);
		} catch (SQLException e) {
			return new Vector<String>();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/collaborators/new")
	public static boolean addCollaborator(@RequestParam String teacherName, @RequestParam String collaboratorName,
										  @RequestParam String gameName, @RequestParam String courseName){
		if(isCollaborator(teacherName, gameName, courseName) == false){
			return false;
		}
		try {
			GameDBModel.addCollaborator(collaboratorName, gameName, courseName);
		} catch (SQLException e) {
			return false;
		}
		LogMessage logMessage = new LogMessage(LogOperation.ADD_COLLABORATOR.getValue(), gameName, gameName, teacherName, courseName, new Date());
		logMessage.setCollaboratorName(collaboratorName);
		try {
			LogController.addLogMessage(logMessage);
		} catch (SQLException e) { //does not return false because the collaborator has been added
			e.printStackTrace();
		}
		return true;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/collaborators/remove")
	public static boolean removeCollaborator(@RequestParam String teacherName, @RequestParam String collaboratorName,
											 @RequestParam String gameName, @RequestParam String courseName){
		if(isCollaborator(teacherName, gameName, courseName) == false){
			return false;
		}
		try {
			GameDBModel.removeCollaborator(collaboratorName, gameName, courseName);
		} catch (SQLException e) {
			return false;
		}
		LogMessage logMessage = new LogMessage(LogOperation.REMOVE_COLLABORATOR.getValue(), gameName, gameName, teacherName, courseName, new Date());
		logMessage.setCollaboratorName(collaboratorName);
		try {
			LogController.addLogMessage(logMessage);
		} catch (SQLException e) { //does not return false because the collaborator has been removed
			e.printStackTrace();
		}
		return true;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/query/collaborators")
	public static boolean isCollaborator(@RequestParam String teacherName, @RequestParam String gameName,
			   					  		 @RequestParam String courseName){
		try {
			return GameDBModel.isCollaborator(teacherName, gameName, courseName);
		} catch (SQLException e) {
			return false;
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/st-comm.com/games/undo")
	public static boolean undoChange(@RequestParam int msgId, @RequestParam String teacherName){
		LogMessage logMessage;
		try {
			logMessage = LogDBModel.fetchMessage(msgId);
		} catch (SQLException e) {
			return false;
		}
		String operation = logMessage.getOperation();
		if(operation.equals(LogOperation.EDIT.getValue())){
			if(uncancelGame(logMessage.getOldGameName(), logMessage.getCourseName(), teacherName) == false){
				return false;
			}
			if(cancelGame(logMessage.getNewGameName(), logMessage.getCourseName(), teacherName) == false){
				//roll back and retrun false
				cancelGame(logMessage.getOldGameName(), logMessage.getCourseName(), teacherName);
				return false;
			}
		}
		else if(operation.equals(LogOperation.CANCEL.getValue())){
			if(uncancelGame(logMessage.getOldGameName(), logMessage.getCourseName(), teacherName) == false){
				return false;
			}
		}
		else if(operation.equals(LogOperation.UNCANCEL.getValue())){
			if(cancelGame(logMessage.getOldGameName(), logMessage.getCourseName(), teacherName) == false){
				return false;
			}
		}
		else if(operation.equals(LogOperation.ADD_COLLABORATOR.getValue())){
			if(removeCollaborator(teacherName, logMessage.getCollaboratorName(), logMessage.getOldGameName(), logMessage.getCourseName()) == false){
				return false;
			}
		}
		else if(operation.equals(LogOperation.REMOVE_COLLABORATOR.getValue())){
			if(addCollaborator(teacherName, logMessage.getCollaboratorName(), logMessage.getOldGameName(), logMessage.getCourseName()) == false){
				return false;
			}
		}
		return false;
	}
}