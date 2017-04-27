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
	
	
	/**
	 * Checks if the user name and password are valid 
	 * @param name
	 * @param password
	 * @return boolean
	 * @throws SQLException
	 */
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

	/**
	 * Checks if username or email already exists in the database
	 * @param name
	 * @param mail
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean exists(String name, String mail) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call registrantExists(?, ?, ?)}");
		callableSt.setString(1, name);
		callableSt.setString(2, mail);
		callableSt.registerOutParameter(3, Types.BIT);
		callableSt.executeUpdate();
		return callableSt.getBoolean(3);
	}

	/**
	 * Sets the user as confirmed user
	 * @param name
	 * @param activationCode
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean setConfirmed(String name, String activationCode) throws SQLException {
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call setConfirmed(?, ?, ?)}");
		callableSt.setString(1, name);
		callableSt.setString(2, activationCode);
		callableSt.registerOutParameter(3, Types.BIT);
		callableSt.executeUpdate();
		return callableSt.getBoolean(3);
	}

	/**
	 * Saves new Registrant Account in the database
	 * @param registrant
	 * @param type
	 * @throws SQLException
	 */
	public static void saveAccount(Registrant registrant, int type) throws SQLException {
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
	}

	/**
	 * Returns the activation code for given registrant name
	 * @param name
	 * @return String
	 * @throws SQLException
	 */
	public static String getActivationCode(String name)throws SQLException {
			AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
		    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall("{call getActivationCode(?, ?)}");
			callableSt.setString(1, name);
			callableSt.registerOutParameter(2, Types.NCHAR);
			callableSt.executeUpdate();
			return callableSt.getString(2);
	}
	
	/**
	 * Returns the type of given registrant name
	 * @param name
	 * @return int
	 * @throws SQLException
	 */
	public static int getRegistrantType(String name) throws SQLException{
		AnnotationConfigApplicationContext configurationContext = new AnnotationConfigApplicationContext(DBConfig.class);
	    JdbcTemplate jdbcTemplate = configurationContext.getBean(JdbcTemplate.class);
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall("{call getRegistrantType(?, ?)}");
		callableSt.setString(1, name);
		callableSt.registerOutParameter(2, Types.INTEGER);
		callableSt.executeUpdate();
		return callableSt.getInt(2);
	}
}