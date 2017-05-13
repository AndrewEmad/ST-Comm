package Controllers;

import java.sql.SQLException;
import java.util.Vector;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import DBModels.LogDBModel;
import Entities.LogMessage;

@CrossOrigin(origins = "*") // allow services of this RestController to share
							// data to any client side request
/**
 * 
 * @author Ahmed Hussein
 *
 */
@RestController
public class LogController {

	@RequestMapping(method=RequestMethod.GET, value="/st-comm.com/games/log")
	public Vector<LogMessage> getLog() {
		try {
			return LogDBModel.fetchLog();
		} catch (SQLException e) {
			return new Vector<LogMessage>();
		}
	}

	public LogMessage getLogMessage(int msgId) {
		try {
			return LogDBModel.fetchMessage(msgId);
		} catch (SQLException e) {
			return null;
		}
	}

	public static void addLogMessage(LogMessage msg) throws SQLException {
		LogDBModel.writeMessage(msg);
	}

	public void deleteLogMessage(int msgId) throws SQLException {
		LogDBModel.removeMessage(msgId);
	}
}
