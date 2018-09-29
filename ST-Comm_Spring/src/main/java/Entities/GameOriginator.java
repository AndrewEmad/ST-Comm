package Entities;

import java.util.Vector;

public class GameOriginator {

	private String gameName;
	private String courseName;
	private int numOfQuestions;
	private String teacherName;
	private Vector<Question> questions;
	private boolean isCancelled;
	private int version;
	
	public Game produceGame(){
		return new Game(gameName, courseName, numOfQuestions,
						teacherName, questions, isCancelled, version);
	}
	
	public void saveStateToGame(Game game){
		this.gameName = game.getName();
		this.courseName = game.getCourseName();
		this.numOfQuestions = game.getNumOfQuestions();
		this.teacherName = game.getTeacherName();
		this.questions = game.getQuestions();
		this.isCancelled = game.isCancelled();
		this.version = game.getVersion();
	}
	public void setInfo(String gameName, String courseName, int numOfQuestions, String teacherName,
			   Vector<Question> questions, boolean isCancelled, int version){
		this.gameName = gameName;
		this.courseName = courseName;
		this.numOfQuestions = numOfQuestions;
		this.teacherName = teacherName;
		this.questions = questions;
		this.isCancelled = isCancelled;
		this.version = version;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setNumOfQuestions(int numOfQuestions) {
		this.numOfQuestions = numOfQuestions;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public void setQuestions(Vector<Question> questions) {
		this.questions = questions;
	}
	public void setCancelled(boolean bool){
		this.isCancelled = bool;
	}
	public void setVersion(int version){
		this.version = version;
	}

	public String getCourseName() {
		return courseName;
	}
}
