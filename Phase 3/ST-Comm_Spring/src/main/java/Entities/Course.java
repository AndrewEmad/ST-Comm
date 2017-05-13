package Entities;

import java.util.Vector;

import DBModels.CourseDBModel;

public class Course {

	  private String name;
	  private Vector<Student> observers;
	  
	  public Course(String name){
		  this.name = name;
		  observers = CourseDBModel.getEnrolledStudents(this.name);
	  }
	  
	  public void notifyAllObservers(String newGameName){
	      for (Student observer : observers) {
	         observer.update("The game \"" + newGameName +
	        		 		 "\" has been added to the course \"" + this.name + "\"");
	      }
	   } 
}