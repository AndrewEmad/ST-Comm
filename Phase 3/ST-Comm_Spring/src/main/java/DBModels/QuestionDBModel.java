package DBModels;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.Vector;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import Config.DBConfig;
import Entities.Question;

public class QuestionDBModel {

	/**
	 * Returns the questions that is related to given game name
	 * @param gameName
	 * @return Vector<Question>
	 * @throws SQLException
	 */
	public static Vector<Question> fetchQuestions(String gameName, String courseName) throws SQLException {

		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call fetchQuestions(?,?)}");
		callableSt.setString(1, gameName);
		callableSt.setString(2, courseName);
		Vector<Question>questions=new Vector<Question>();
		ResultSet resultQuestions = callableSt.executeQuery();
		while(resultQuestions.next()){
			Question question = new Question();
			Vector<String>choices=getChoices(resultQuestions.getInt(1));
			int correctAnswer=choices.indexOf(resultQuestions.getString(3));
			
			 question.setInfo(choices, correctAnswer, resultQuestions.getString(2),resultQuestions.getInt(4));
			
			questions.add(question);
		}
		return questions;
	}

	
	/**
	 * Saves a question for specific game
	 * @param question
	 * @param gameName
	 * @throws SQLException
	 */
	public static void saveQuestion(Question question, String gameName,String courseName) throws SQLException {

		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call saveQuestion(?, ?, ?, ?, ?)}");
		callableSt.setString(1, question.getQuestionStatement());
		callableSt.setInt(2, question.getTime());
		callableSt.setString(3,gameName);
		callableSt.setString(4, courseName);
		callableSt.registerOutParameter(5, Types.INTEGER);
		callableSt.executeUpdate();
		saveChoices(callableSt.getInt(5),question.getChoices());
	}

	/**
	 * Returns the choices for given questionID
	 * @param questionID
	 * @return Vector<String>
	 * @throws SQLException
	 */
	private static Vector<String> getChoices(int questionID) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call fetchChoices(?)}");
		callableSt.setInt(1, questionID);
		Vector<String>choices=new Vector<String>();
		ResultSet resultChoices = callableSt.executeQuery();
		while(resultChoices.next()){
			choices.add(resultChoices.getString(1));
		}
		return choices;
	}
	
	/**
	 * Saves Choices of specific question
	 * @param questionID
	 * @param choices
	 * @throws SQLException
	 */
	private static void saveChoices(int questionID,Vector<String>choices) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		for(int i=0;i<choices.size();++i){
			CallableStatement callableSt = connection.prepareCall("{call saveChoices(?,?)}");
			callableSt.setInt(1, questionID);
			callableSt.setString(2, choices.get(i));
			callableSt.executeUpdate();
		}
	}




}