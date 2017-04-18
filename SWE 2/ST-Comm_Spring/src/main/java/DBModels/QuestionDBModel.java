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
import Entities.Question;

public class QuestionDBModel {

	public static Vector<Question> fetchQuestions(String gameName) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call fetchQuestions(?)}");
		callableSt.setString(1, gameName);
		Vector<Question>questions=new Vector<Question>();
		ResultSet resultQuestions = callableSt.executeQuery();
		while(resultQuestions.next()){
			Question question = new Question();
			Vector<String>choices=getChoices(resultQuestions.getInt(1));
			int correctAnswer=choices.indexOf(resultQuestions.getString(3));
			question.setInfo(choices, correctAnswer, resultQuestions.getString(2),resultQuestions.getTime(4));
			questions.add(question);
		}
		return questions;
	}

	public static boolean saveQuestion(Question question, String gameName) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call saveQuestion(?, ?, ?)}");
		callableSt.setString(1, question.getQuestionStatement());
		callableSt.setTime(2, question.getTime());
		callableSt.registerOutParameter(3, Types.INTEGER);
		return callableSt.executeUpdate()==0&&saveChoices(callableSt.getInt(3),question.getChoices());
	}

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
	
	private static boolean saveChoices(int questionID,Vector<String>choices) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		boolean success=true;
		for(int i=0;i<choices.size()&&success;++i){
			CallableStatement callableSt = connection.prepareCall("{call saveChoices(?,?)}");
			callableSt.setInt(1, questionID);
			callableSt.setString(2, choices.get(i));
			success=callableSt.executeUpdate()==0;
		}
		return success;
	}




}