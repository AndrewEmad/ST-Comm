package automatedTesting;

import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		AddCollaborator addCollaborator=new AddCollaborator();
		addCollaborator.addCollaborator();
		Thread.sleep(60000);
		
		CopyGame copyGame=new CopyGame();
		copyGame.copyGame();
		Thread.sleep(60000);
		
		EditGame editGame=new EditGame();
		editGame.editGame();
		Thread.sleep(60000);
		
		CancelGame cancelGame=new CancelGame();
		cancelGame.cancelGame();
		Thread.sleep(60000);
		
		TrackGameChanges trackChanges=new TrackGameChanges();
		trackChanges.trackGameChanges();
		Thread.sleep(60000);
	}
}
