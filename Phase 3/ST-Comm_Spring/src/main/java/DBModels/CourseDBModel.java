package DBModels;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import Config.DBConfig;

public class CourseDBModel {

	/**
	 * Returns the games that is created in the given course
	 * @param courseName
	 * @return Vector<String>
	 * @throws SQLException
	 */
	public static Vector<String> fetchGames(String courseName) throws SQLException {

		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call fetchCourseGames(?)}");
		callableSt.setString(1, courseName);
		Vector<String>games=new Vector<String>();
		ResultSet resultGames = callableSt.executeQuery();
		while(resultGames.next()){
			games.add(resultGames.getString(1));
		}
		return games;
	}

	/**
	 * Saves new course in the database
	 * @param courseName
	 * @param teacherName
	 * @throws SQLException
	 */
	public static void saveCourse(String courseName, String teacherName) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call saveCourse(?, ?)}");
		callableSt.setString(1, courseName);
		callableSt.setString(2, teacherName);
		callableSt.executeUpdate();
	}

	/**
	 * Register user in course
	 * @param courseName
	 * @param registrantName
	 * @throws SQLException
	 */
	public static void enroll(String courseName, String registrantName) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call Enroll(?, ?)}");
		callableSt.setString(1, courseName);
		callableSt.setString(2, registrantName);
		callableSt.executeUpdate();
	}

	public static boolean isEnrolled(String courseName, String collaboratorName){
		/*
		 * 
		 * 
		 */
		return false;
	}
	public static Vector<String> getEnrolledStudents(String courseName) throws SQLException{
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call getEnrolledStudents(?)}");
		callableSt.setString(1, courseName);
		Vector<String>students=new Vector<String>();
		ResultSet resultStudents = callableSt.executeQuery();
		while(resultStudents.next()){
			students.add(resultStudents.getString(1));
		}
		return students;
		
	}
	/**
	 * Returns all courses in the database
	 * @return Vector<String>
	 * @throws SQLException
	 */
	public static Vector<String> fetchCourses() throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call fetchCourses}");
		Vector<String>courses=new Vector<String>();
		ResultSet resultCourses = callableSt.executeQuery();
		while(resultCourses.next()){
			courses.add(resultCourses.getString(1));
		}
		return courses;
	}
	
	
	/**
	 * Returns courses for specific user
	 * @param registrantName
	 * @return Vector<String>
	 * @throws SQLException
	 */
	public static Vector<String> fetchCourses(String registrantName) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call fetchRegistrantCourses(?)}");
		callableSt.setString(1, registrantName);
		Vector<String>courses=new Vector<String>();
		ResultSet resultCourses = callableSt.executeQuery();
		while(resultCourses.next()){
			courses.add(resultCourses.getString(1));
		}
		return courses;
	}
	
	
	
	/**
	 * Checks if the course name already exists
	 * @param courseName
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean exists(String courseName) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call courseExists(?, ?)}");
		callableSt.setString(1, courseName);
		callableSt.registerOutParameter(2, Types.BIT);
		callableSt.executeUpdate();
		return callableSt.getBoolean(2);
	}
}