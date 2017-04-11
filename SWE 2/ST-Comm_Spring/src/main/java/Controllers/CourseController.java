package Controllers;

import java.util.Vector;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import DBModels.CourseDBModel;

@CrossOrigin(origins = "*") //allow services of this RestController to share data to 
							//any client side request
@RestController
public class CourseController {

//	public Vector<String> getGames(String courseName) {
//		return null;
//	}

	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/new")
	public boolean createCourse(String courseName, String teacherName) {
		return CourseDBModel.saveCourse(courseName, teacherName);
	}

	@RequestMapping("/st-comm.com/courses/list-all")
	public Vector<String> getCourses() {
		return CourseDBModel.fetchCourses();
	}

	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/list-by-registrant")
	public Vector<String> getCourses(String registrantName) {
		return CourseDBModel.fetchCourses(registrantName);
	}

	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/register")
	public boolean register(String studentName, String courseName) {
		return CourseDBModel.enroll(courseName, studentName);
	}

}