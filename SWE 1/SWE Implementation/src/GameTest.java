import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GameTest {
	
	game g = new game();

	@DataProvider(name="testcases1")
	public static Object[][] MCQ_testcases(){
		return new Object[][]{{" correct answer :D your score is 1\n"
				+ " correct answer :D your score is 2\n"
				+ " correct answer :D your score is 3\n"
				+ " correct answer :D your score is 4\n","10\n24\n5\n15\n"},
				
				{" correct answer :D your score is 1\n"
				+ " correct answer :D your score is 2\n"
				+ " correct answer :D your score is 3\n"
				+ " wrong answer :( your score 3\n"
				,"10\n24\n5\n151\n"},
				
				{" correct answer :D your score is 1\n"
						+ " correct answer :D your score is 2\n"
						+ " correct answer :D your score is 3\n"
						+ " correct answer :D your score is 4\n","10\n24\n\n5\n15\n"}
		
		};
	}
	
	@Test(dataProvider="testcases1")
	 public void MCQgame1math(String outputs , String input) {
		
		InputStream stdin = System.in;
		PrintStream stdout = System.out;
		try {

			System.setIn(new ByteArrayInputStream((input).getBytes()));
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			System.setOut(new PrintStream(output));
			g.MCQgame1math();

			String answer[] = output.toString().split("\r\n");
			
			Assert.assertEquals(answer[3]+"\n"+answer[6]+"\n"+answer[9]
							+"\n"+answer[12]+"\n" , outputs);
			
		} finally {
			System.setIn(stdin);
			System.setOut(stdout);
		}


	 }

	@DataProvider(name="testcases2")
	public static Object[][] TF_testcases(){
		return new Object[][]{{" correct answer :D your score is 1\n"
				+ " correct answer :D your score is 2\n"
				+ " correct answer :D your score is 3\n"
				+ " correct answer :D your score is 4\n","T\nF\nF\nT\n"},
				
				{" correct answer :D your score is 1\n"
				+ " correct answer :D your score is 2\n"
				+ " wrong answer :( your score 2\n"
				+ " correct answer :D your score is 3\n","T\nF\nT\nT\n"},
				
				{" correct answer :D your score is 1\n"
						+ " correct answer :D your score is 2\n"
						+ " correct answer :D your score is 3\n"
						+ " correct answer :D your score is 4\n","T\nF\n\nF\nT\n"},
				
				{" correct answer :D your score is 1\n"
						+ " correct answer :D your score is 2\n"
						+ " correct answer :D your score is 3\n"
						+ " correct answer :D your score is 4\n","true\nfalse\nfalse\ntrue\n"},
				
							{" correct answer :D your score is 1\n"
								+ " correct answer :D your score is 2\n"
								+ " correct answer :D your score is 3\n"
								+ " correct answer :D your score is 4\n","t\nf\nf\nt\n"}
		
		};
	}

	@Test(dataProvider="testcases2" )
	public void TFgame1math(String outputs , String input) {

		InputStream stdin = System.in;
		PrintStream stdout = System.out;
		try {

			System.setIn(new ByteArrayInputStream((input).getBytes()));
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			System.setOut(new PrintStream(output));
			g.TFgame1math();
			
			String answer[]= output.toString().split("\r\n");
			
			if(answer.length>12){
				Assert.assertEquals(answer[3]+"\n"+answer[6]+"\n"+answer[9]
						+"\n"+answer[12]+"\n",outputs);
			}
			else if(answer.length<10){
				Assert.assertEquals(answer[3]+"\n"+answer[6]+"\n",outputs);
			}
			else{
				Assert.assertEquals(answer[3]+"\n"+answer[6]+"\n"+answer[9]
						+"\n",outputs);
			}
		} finally {
			System.setIn(stdin);
			System.setOut(stdout);
		}

	}


	

	@DataProvider(name="DATA")
	public Object[][] gameData(){
		return new Object[][]{
			{"qwwq","b","1 2 0","game is added successfully :D "},
			{"","","1 2 0","Invalid Teacher & Game Names ! "},
			{"","b","1 2 0","Invalid Teacher Name ! "},
			{"qwwq","","1 2 0","Invalid Game Name ! "},
			{"a","b","1 1 0","game is added successfully :D "},
			{"a","b","1 2 1 \n\nStatement\nC1\nC2\nC3\nC4\nC4\n","game is added successfully :D "},
			{"a","b","1 2 1 \n\nStatement\nC1\nC2\nC3\nC4\nC5\n","No Such Choice ! "},
		};
	}
	
	
	@Test(dataProvider="DATA")
	public void addGameTest(String teacherName,String gameName,String input,String result) {
		InputStream stdin = System.in;
		PrintStream stdout = System.out;
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		
		try{g.addgame(teacherName, gameName);}
		finally{
			System.setIn(stdin);
			System.setOut(stdout);
		}
		
		String answer[] = output.toString().split("\r\n");
		Assert.assertEquals(answer[answer.length-1], result);
		
	}
}
