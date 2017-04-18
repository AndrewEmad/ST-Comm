package Entities;

import java.util.Vector;

public class Question {

  private Vector<String> choices;
  private int correctAnswer;
  private String questionStatement;

  public void Question() {
  }
  
  public void setInfo(Vector<String> choices, int correctAnswer, String questionStatement) {
	  this.choices = choices;
	  this.correctAnswer = correctAnswer;
	  this.questionStatement = questionStatement;
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

  public void setCorrectAnswer(int correctAnswer) {
	  this.correctAnswer = correctAnswer;
  }

  public void setQuestionStatement(String questionStatement) {
	  this.questionStatement = questionStatement;
  }
}