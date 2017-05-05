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
/**
 * 
 * @author Ahmed Hussein
 *
 */
@RestController
public class CourseController {

	/**
	 * 
	 * @param courseName: the name of the course for which game names are to be given
	 * @return vector of game names that exists in a specific course
	 */
	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/games/courses/list-by-course")
	public Vector<String> getGames(@RequestParam String courseName) {
		try {
			return GameCache.loadCache(courseName);
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * 
	 * @param courseName: the name of the course to be created
	 * @param teacherName: the creator of the game
	 * @return true if the course was created successfully, and false otherwise
	 */
	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/new")
	public boolean createCourse(@RequestParam String courseName, @RequestParam String teacherName) {
		try {
			CourseDBModel.saveCourse(courseName, teacherName);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param registrantName
	 * @return vector of the names of the courses that the registrant did not enroll in yet
	 */
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

	/**
	 * 
	 * @param registrantName
	 * @return vector of the names of the courses that the registrant has enrolled in
	 */
	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/list-by-registrant")
	public Vector<String> getCourses(@RequestParam String registrantName) {
		try {
			return CourseDBModel.fetchCourses(registrantName);
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * 
	 * @param registrantName
	 * @param courseName: the course in which the registrant wishes to enroll
	 * @return true if the registrant was enrolled successfully, and false otherwise
	 */
	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/courses/register")
	public boolean register(@RequestParam String registrantName, @RequestParam String courseName) {
		try {
			CourseDBModel.enroll(courseName, registrantName);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param courseName: the course being checked whether it exists in the system or not
	 * @return true if the course exists, and false otherwise
	 */
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