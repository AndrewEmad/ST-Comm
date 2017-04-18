package Entities;

import java.util.Vector;

public class Game {

	private String name;
	private String courseName;
	private int numOfQuestions;
	private String teacherName;
	private Vector<Question> questions;

	public void setInfo(String name, String courseName, String teacherName,
						Vector<Question> questions) {
		this.name = name;
		this.courseName = courseName;
		this.teacherName = teacherName;
		this.questions = questions;
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
