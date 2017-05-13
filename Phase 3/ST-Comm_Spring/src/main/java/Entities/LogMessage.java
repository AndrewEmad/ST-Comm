package Entities;

import java.util.Date;

public class LogMessage {
	private int id;
	private String operation;
	private String oldGameName;
	private String newGameName;
	private String teacherName;
	private String courseName;
	private String collaboratorName;
	private Date date;

	public LogMessage(){}
	public LogMessage(String operation, String oldGameName, String newGameName,
					  String teacherName, String courseName, Date date) {
		this.operation = operation;
		this.oldGameName = oldGameName;
		this.newGameName = newGameName;
		this.teacherName = teacherName;
		this.courseName = courseName;
		this.collaboratorName = "";
		this.date = date;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getOldGameName() {
		return oldGameName;
	}
	public void setOldGameName(String oldGameName) {
		this.oldGameName = oldGameName;
	}
	public String getNewGameName() {
		return newGameName;
	}
	public void setNewGameName(String newGameName) {
		this.newGameName = newGameName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCollaboratorName() {
		return collaboratorName;
	}
	public void setCollaboratorName(String collaboratorName) {
		this.collaboratorName = collaboratorName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
