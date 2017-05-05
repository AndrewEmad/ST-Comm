package Entities;

import java.util.Vector;

public class Game implements Cloneable{

	private String name;
	private String courseName;
	private int numOfQuestions;
	private String teacherName;
	private Vector<Question> questions;
	boolean isCancelled;
	private int version;

	public Game(String name, String courseName, int numOfQuestions, String teacherName,
						Vector<Question> questions, boolean isCancelled, int version){
		this.name = name;
		this.courseName = courseName;
		this.numOfQuestions = numOfQuestions;
		this.teacherName = teacherName;
		this.questions = questions;
		this.isCancelled = isCancelled;
		this.version = version;
	}

	public int getNumOfQuestions() {
		return numOfQuestions;
	}

	public String getName() {
		return name;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public Vector<Question> getQuestions() {
		return questions;
	}

	public String getCourseName() {
		return courseName;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public int getVersion() {
		return version;
	}
	
	public Game clone() throws CloneNotSupportedException{
		Object clone = null;
		clone = super.clone();
		return (Game)clone;
	}
}
