package automatedTesting;

import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		CopyGame copyGame=new CopyGame();
		copyGame.copyGame();
		CancelGame cancelGame=new CancelGame();
		cancelGame.cancelGame();
	}
}
