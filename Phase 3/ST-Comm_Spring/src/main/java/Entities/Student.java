package Entities;

import Controllers.RegistrantController;

public class Student extends Registrant {

	private int score;
	private Course course;
	
	Student(Course course, String studentName){
		this.course = course;
		this.name = studentName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void update(String notificationMsg){
		RegistrantController.sendNotification(notificationMsg, this.name);
	}
}