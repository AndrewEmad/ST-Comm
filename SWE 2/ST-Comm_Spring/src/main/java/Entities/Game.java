package Entities;

import java.util.Vector;

public class Game {

	private String name;
	private int numOfQuestions;
	private String teacherName;
	private Vector<Question> questions;

	public void setInfo(String name, Vector<Question> questions, String teacherName) {
		this.name = name;
		this.questions = questions;
		this.teacherName = teacherName;
	}

	public int getNumOfQuestions() {
		return numOfQuestions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumOfQuestions(int numOfQuestions) {
		this.numOfQuestions = numOfQuestions;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Vector<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Vector<Question> questions) {
		this.questions = questions;
	}
}
