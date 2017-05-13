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
import Entities.Game;

public class GameDBModel {

	/**
	 * Saves new Game in the Database
	 * @param game
	 * @throws SQLException
	 */
	public static void saveGameVersion(Game game) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call saveGame(?, ?, ?, ?)}");
		callableSt.setString(1, game.getName());
		callableSt.setString(2, game.getCourseName());
		callableSt.setInt(3, game.getNumOfQuestions());
		callableSt.setString(4, game.getTeacherName());
		callableSt.executeUpdate();
	}

	public static void saveComment(String gameName, String courseName, String comment) throws SQLException{
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call saveComment(?, ?, ?)}");
		callableSt.setString(1, gameName);
		callableSt.setString(2, courseName);
		callableSt.setString(3, comment);
		callableSt.executeUpdate();
	}
	
	public static Vector<String> fetchComments(String gameName, String courseName) throws SQLException{
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call fetchComments(?,?)}");
		callableSt.setString(1, gameName);
		callableSt.setString(2, courseName);
		Vector<String>comments=new Vector<String>();
		ResultSet resultComments = callableSt.executeQuery();
		while(resultComments.next()){
			comments.add(resultComments.getString(1));
		}
		return comments;
	}
	
	public static void addCollaborator(String collaboratorName, String gameName, String CourseName) throws SQLException{
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call addCollaborator(?, ?, ?)}");
		callableSt.setString(1, collaboratorName);
		callableSt.setString(2, gameName);
		callableSt.setString(3, CourseName);
		callableSt.executeUpdate();
	}
	
	public static void removeCollaborator(String collaboratorName, String gameName, String CourseName) throws SQLException{
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call removeCollaborator(?, ?, ?)}");
		callableSt.setString(1, collaboratorName);
		callableSt.setString(2, gameName);
		callableSt.setString(3, CourseName);
		callableSt.executeUpdate();
	}
	
	
	public static boolean isCollaborator(String teacherName, String gameName, String CourseName)throws SQLException{
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call isCollaborator(?, ?, ?, ?)}");
		callableSt.setString(1, teacherName);
		callableSt.setString(2, gameName);
		callableSt.setString(3, CourseName);
		callableSt.registerOutParameter(4, Types.BIT);
		callableSt.executeUpdate();
		return callableSt.getBoolean(4);
	}
	/**
	 * Retrieve specific game from database
	 * @param gameName
	 * @return Game
	 * @throws SQLException
	 */
	public static Game fetchGame(String gameName,String courseName) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call fetchGame(?,?)}");
		callableSt.setString(1, gameName);
		callableSt.setString(2, courseName);
		ResultSet resultGame= callableSt.executeQuery();
		Game game = null;
		if(resultGame.next())
			game = new Game(resultGame.getString("GameName"),resultGame.getString("CourseName"),
					resultGame.getInt("NumberOfQuestions"),resultGame.getString("TeacherName"),null,
					resultGame.getBoolean("Canceled"),resultGame.getInt("Version") );
		return game;
	}

	/**
	 * Saves student score in specific game
	 * @param name
	 * @param score
	 * @param gameName
	 * @throws SQLException
	 */
	public static void saveScore(String name, int score, String gameName, String courseName) throws SQLException {
		
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call saveScore(?, ?, ?, ?)}");
		callableSt.setString(1, name);
		callableSt.setInt(2, score);
		callableSt.setString(3, gameName);
		callableSt.setString(4, courseName);
		callableSt.executeUpdate();
	}
	
	/**
	 * Checks if game name already exists in the database
	 * @param gameName
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean exists(String gameName, String courseName) throws SQLException{
		
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call gameExists(?,?, ?)}");
		callableSt.setString(1, gameName);
		callableSt.setString(2, courseName);
		callableSt.registerOutParameter(3, Types.BIT);
		callableSt.executeUpdate();
		return callableSt.getBoolean(3);
	}
	
	public static void cancelGame(String gameName,String courseName) throws SQLException{
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call cancelGame(?,?)}");
		callableSt.setString(1, gameName);
		callableSt.setString(2, courseName);
		callableSt.executeUpdate();
	}
	
	public static void uncancelGame(String gameName,String courseName) throws SQLException{
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call uncancelGame(?,?)}");
		callableSt.setString(1, gameName);
		callableSt.setString(2, courseName);
		callableSt.executeUpdate();
	}
}