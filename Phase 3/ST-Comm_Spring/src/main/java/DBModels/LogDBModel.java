package DBModels;

import java.util.Vector;

import Entities.LogMessage;

public class LogDBModel {

	//NOTE: each log message has id (auto increment in DB)
	public static Vector<LogMessage> fetchLog(){
		//retrieves all log history from DB
		return null;
	}
	
	public static LogMessage fetchMessage(int msgId){
		//fetch specific log message from DB
		return null;
	}
	
	public static void writeMessage(LogMessage msg){
		//save the message to log table
	}
	
	public static void removeMessage(int msgId){
		//remove specific log message from DB
	}
}
