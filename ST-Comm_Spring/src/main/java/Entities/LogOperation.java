package Entities;

public enum LogOperation {

	EDIT("Edited"),
	CANCEL("Cancelled"),
	UNCANCEL("Uncancelled"),
	ADD_COLLABORATOR("Collaborator Added"),
	REMOVE_COLLABORATOR("Collaborator Removed");
	
	private final String value;
	
	LogOperation(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
