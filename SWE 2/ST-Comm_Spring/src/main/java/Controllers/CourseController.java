package Controllers;

import java.sql.SQLException;

import java.util.Vector;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import DBModels.CourseDBModel;

@CrossOrigin(origins = "*") //allow services of this RestController to share data to 
							//any client side request
@RestController
public class CourseController {

	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/games/courses/list-by-course")
	public Vector<String> getGames(@RequestParam String courseName) {
		try {
			return CourseDBModel.fetchGames(courseName);
		} catch (SQLException e) {
			return null;
		}
	}

	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/new")
	public boolean createCourse(@RequestParam String courseName, @RequestParam String teacherName) {
		try {
			CourseDBModel.saveCourse(courseName, teacherName);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/list-all")
	public Vector<String> getOtherCourses(@RequestParam String registrantName) {
		try {
			String course;
			Vector<String> registeredIn = CourseDBModel.fetchCourses(registrantName); 
			Vector<String> allCourses = CourseDBModel.fetchCourses();
			Vector<String> otherCourses = new Vector<String>();
			for(int i=0; i<allCourses.size(); i++){
				course = allCourses.get(i);
				if(registeredIn.contains(course) == false){
					otherCourses.add(course);
				}
			}
			return otherCourses;
		} catch (SQLException e) {
			return null;
		}
	}

	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/list-by-registrant")
	public Vector<String> getCourses(@RequestParam String registrantName) {
		try {
			return CourseDBModel.fetchCourses(registrantName);
		} catch (SQLException e) {
			return null;
		}
	}

	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/register")
	public boolean register(@RequestParam String registrantName, @RequestParam String courseName) {
		try {
			CourseDBModel.enroll(courseName, registrantName);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/exists")
	public boolean exists(@RequestParam String courseName){
		try {
			CourseDBModel.exists(courseName);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
}