package DBModels;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import Config.DBConfig;
import Entities.Registrant;


public class RegistrantDBModel {
	
	
	public static boolean authenticate(String name, String password) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call Authenticate(?, ?, ?)}");
		callableSt.setString(1, name);
		callableSt.setString(2, password);
		callableSt.registerOutParameter(3, Types.BIT);
		callableSt.executeUpdate();
		return callableSt.getBoolean(3);
	}

	public static boolean exists(String name, String mail) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call Exist(?, ?, ?)}");
		callableSt.setString(1, name);
		callableSt.setString(2, mail);
		callableSt.registerOutParameter(3, Types.BIT);
		callableSt.executeUpdate();
		return callableSt.getBoolean(3);
	}

	public static boolean setConfirmed(String name, String activationCode) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call Exist(?, ?, ?)}");
		callableSt.setString(1, name);
		callableSt.setString(2, activationCode);
		callableSt.registerOutParameter(3, Types.BIT);
		callableSt.executeUpdate();
		return callableSt.getBoolean(3);
	}

	public static boolean saveAccount(Registrant registrant, int type) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call saveAccount(?, ?, ?, ?, ?, ?, ?)}");
		callableSt.setString(1, registrant.getName());
		callableSt.setInt(2, type);
		callableSt.setString(3, registrant.getCountry());
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        callableSt.setDate(4, java.sql.Date.valueOf(s.format(registrant.getBirthdate())));
		callableSt.setString(5, registrant.getGender());
		callableSt.setString(6, registrant.getMail());
		callableSt.setString(7, registrant.getPassword());
		callableSt.executeUpdate();
		return callableSt.executeUpdate()==0;
	}

	public static String getActivationCode(String name)throws SQLException {
			AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall("{call getActivationCode(?, ?)}");
			callableSt.setString(1, name);
			callableSt.registerOutParameter(2, Types.NCHAR);
			callableSt.executeUpdate();
			return callableSt.getString(3);
	}
}