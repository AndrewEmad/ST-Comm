package Entities;

import java.sql.SQLException;
import java.util.Vector;

import DBModels.CourseDBModel;

public class Course {

	  private String name;
	  private Vector<Student> observers;
	  
	  public Course(String name) throws SQLException{
		  this.name = name;
		  Vector<String> observerNames = CourseDBModel.getEnrolledStudents(this.name);
		  for(int i=0; i < observerNames.size(); i++){
			  observers.add(new Student(observerNames.get(i)));
		  }
	  }
	  
	  public void notifyAllObservers(String newGameName){
	      for (Student observer : observers) {
	         observer.update("The game \"" + newGameName +
	        		 		 "\" has been added to the course \"" + this.name + "\"");
	      }
	   } 
}