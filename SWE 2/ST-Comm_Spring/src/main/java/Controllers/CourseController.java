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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/new")
	public boolean createCourse(@RequestParam String courseName, @RequestParam String teacherName) {
		try {
			return CourseDBModel.saveCourse(courseName, teacherName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/list-by-registrant")
	public Vector<String> getCourses(@RequestParam String registrantName) {
		try {
			return CourseDBModel.fetchCourses(registrantName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/register")
	public boolean register(@RequestParam String studentName, @RequestParam String courseName) {
		try {
			return CourseDBModel.enroll(courseName, studentName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}