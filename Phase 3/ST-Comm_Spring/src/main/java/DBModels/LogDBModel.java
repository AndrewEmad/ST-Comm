package DBModels;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import Config.DBConfig;
import Entities.LogMessage;
import Entities.Question;

public class LogDBModel {

	//NOTE: each log message has id (auto increment in DB)
	public static Vector<LogMessage> fetchLog() throws SQLException{
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call fetchLog}");
		Vector<LogMessage>messages=new Vector<LogMessage>();
		ResultSet resultMessages = callableSt.executeQuery();
		while(resultMessages.next()){
			LogMessage msg = new LogMessage(resultMessages.getInt(1),resultMessages.getString(2)
					,resultMessages.getString(3),resultMessages.getString(4)
					,resultMessages.getString(5),resultMessages.getString(6)
					,Date.valueOf(resultMessages.getDate(8).toLocalDate()));
			String collaborator=resultMessages.getString(7);
			if(!resultMessages.wasNull())
				msg.setCollaboratorName(collaborator);
			messages.add(msg);
		}
		return messages;
	}
	
	public static LogMessage fetchMessage(int msgId) throws SQLException{
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call fetchMessage(?)}");
		callableSt.setInt(1, msgId);
		ResultSet resultMessages = callableSt.executeQuery();
		if(resultMessages.next()){
			LogMessage msg = new LogMessage(resultMessages.getInt(1),resultMessages.getString(2)
					,resultMessages.getString(3),resultMessages.getString(4)
					,resultMessages.getString(5),resultMessages.getString(6)
					,resultMessages.getDate(8));
			String collaborator=resultMessages.getString(7);
			if(!resultMessages.wasNull())
				msg.setCollaboratorName(collaborator);
			return msg;
		}
		return null;
	}
	
	public static void writeMessage(LogMessage msg) throws SQLException{
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call writeMessage(?,?,?,?,?,?,?)}");
		callableSt.setString(1, msg.getOperation());
		callableSt.setString(2, msg.getOldGameName());
		callableSt.setString(3, msg.getNewGameName());
		callableSt.setString(4, msg.getCourseName());
		callableSt.setString(5, msg.getTeacherName());
		if(msg.getCollaboratorName()==null||msg.getCollaboratorName()=="")
			callableSt.setNull(6, Types.NVARCHAR);
		else
			callableSt.setString(6, msg.getCollaboratorName());
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        callableSt.setDate(7, java.sql.Date.valueOf(s.format(msg.getDate())));
		callableSt.executeUpdate();
		
	}
	
	public static void removeMessage(int msgId) throws SQLException{
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call removeMessage(?)}");
		callableSt.setInt(1, msgId);
		callableSt.executeLargeUpdate();
		
	}
}
