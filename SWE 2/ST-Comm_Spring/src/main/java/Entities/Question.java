package Entities;

import java.sql.Time;
import java.util.Vector;

public class Question {

  private Vector<String> choices;
  private int correctAnswer;
  private String questionStatement;
  private Time time;
  

public void Question() {
  }
  
  public void setInfo(Vector<String> choices, int correctAnswer, String questionStatement, Time time) {
	  this.choices = choices;
	  this.correctAnswer = correctAnswer;
	  this.questionStatement = questionStatement;
	  this.time=time;
  }

  public Vector<String> getChoices() {
	  return choices;
  }

  public int getCorrectAnswer() {
	  return correctAnswer;
  }

  public String getQuestionStatement() {
	  return questionStatement;
  }

  public void setChoices(Vector<String> choices) {
	  this.choices = choices;
  }
  
  public Time getTime() {
		return time;
	  }

  public void setTime(Time time) {
	this.time = time;
  }

  public void setCorrectAnswer(int correctAnswer) {
	  this.correctAnswer = correctAnswer;
  }

  public void setQuestionStatement(String questionStatement) {
	  this.questionStatement = questionStatement;
  }
}