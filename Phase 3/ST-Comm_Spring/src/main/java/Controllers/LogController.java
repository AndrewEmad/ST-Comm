package Controllers;

import java.util.Vector;

import org.springframework.web.bind.annotation.CrossOrigin;
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

	public Vector<LogMessage> getLog() {
		return LogDBModel.fetchLog();
	}

	public LogMessage getLogMessage(int msgId) {
		return LogDBModel.fetchMessage(msgId);
	}

	public static void addLogMessage(LogMessage msg) {
		LogDBModel.writeMessage(msg);
	}

	public void deleteLogMessage(int msgId) {
		LogDBModel.removeMessage(msgId);
	}
}
