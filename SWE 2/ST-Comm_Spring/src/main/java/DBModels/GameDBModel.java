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
import Entities.Question;

public class GameDBModel {

	public static boolean saveGame(Game game) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call saveGame(?, ?, ?)}");
		callableSt.setString(1, game.getName());
		callableSt.setInt(2, game.getNumOfQuestions());
		callableSt.setString(3, game.getTeacherName());
		boolean success=callableSt.executeUpdate()==0;
		for(int i=0;i<game.getNumOfQuestions()&&success;++i){
			success=QuestionDBModel.saveQuestion(game.getQuestions().get(i), game.getName());
		}
		return success;
	}

	public static Game fetchGame(String gameName) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call fetchGame(?)}");
		callableSt.setString(1, gameName);
		ResultSet resultGame= callableSt.executeQuery();
		Game game=new Game();
		/*Commented By Ahmed Hussein
		game.setInfo(resultGame.getString(1),QuestionDBModel.fetchQuestions(gameName),resultGame.getString(4) );
		*/
		return game;
	}

	public static boolean saveScore(String name, int score, String gameName) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call saveScore(?, ?, ?)}");
		callableSt.setString(1, name);
		callableSt.setInt(2, score);
		callableSt.setString(3, gameName);
		return callableSt.executeUpdate()==0;
	}
	
	public static boolean exists(String gameName){
		return false;
	}
}