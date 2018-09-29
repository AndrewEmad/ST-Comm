package Entities;

import Controllers.RegistrantController;

public class Student extends Registrant {

	private int score;
	
	Student(String studentName){
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