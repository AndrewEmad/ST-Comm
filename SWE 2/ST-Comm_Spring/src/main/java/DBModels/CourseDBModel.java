package DBModels;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import Config.DBConfig;

public class CourseDBModel {

	public static Vector<String> fetchGames(String courseName) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call fetchCourseGames(?)}");
		callableSt.setString(1, courseName);
		Vector<String>games=new Vector<String>();
		ResultSet resultGames = callableSt.executeQuery();
		while(resultGames.next()){
			games.add(resultGames.getString(0));
		}
		return games;
	}

	public static void saveCourse(String courseName, String teacherName) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call saveCourse(?, ?)}");
		callableSt.setString(1, courseName);
		callableSt.setString(2, teacherName);
		callableSt.executeUpdate();
	}

	public static void enroll(String courseName, String registrantName) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call Enroll(?, ?)}");
		callableSt.setString(1, courseName);
		callableSt.setString(2, registrantName);
		callableSt.executeUpdate();
	}

	public static Vector<String> fetchCourses() throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call fetchCourses}");
		Vector<String>courses=new Vector<String>();
		ResultSet resultCourses = callableSt.executeQuery();
		while(resultCourses.next()){
			courses.add(resultCourses.getString(2));
		}
		return courses;
	}

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