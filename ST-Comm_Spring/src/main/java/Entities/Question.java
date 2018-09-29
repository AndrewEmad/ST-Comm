package Entities;

import java.util.Vector;

public class Question {

	private Vector<String> choices;
	private int correctAnswer;
	private String questionStatement;
	private int time;

	public void Question() {
	}

	public void setInfo(Vector<String> choices, int correctAnswer, String questionStatement,
						int time) {
		this.choices = choices;
		this.correctAnswer = correctAnswer;
		this.questionStatement = questionStatement;
		this.time = time;
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

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public void setQuestionStatement(String questionStatement) {
		this.questionStatement = questionStatement;
	}
}