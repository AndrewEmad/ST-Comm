import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.testng.Assert;

public class SignUpTest {
	
	@DataProvider(name = "RegisterationFormProvider")
	public Object[][] RegisterationFormProvider(){
		return new Object[][]{
			{1,"ahmed\nhussein\nexample1@gmail.com\nmyPassword\nmale\n21\n","Registeration Success"},
			{1,"ahmed\nmohamed\n\n\nmale\n20\n","Enter information again"},
			{1,"ahmed\nmohamed\nmymail@gmail.com\nhisPassword\nmale\n-20\n","Registration failed"},
			{1,"ahmed\nhussein\nexample1@gmail.com\nmyPassword\nmale\n21\nahmed\nhussein\nexample2@gmail.com\nmyPassword\nmale\n21\n","Registration failed"}
		};
	}

	@Test(dataProvider="RegisterationFormProvider")
	public void RegisterationFormTest(int kind, String input, String result) {
		InputStream stdin = System.in;
		PrintStream stdout = System.out;
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		Interfacee interfacee = new Interfacee();
		try {
			interfacee.RegisterationForm(kind);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			System.setIn(stdin);
			System.setOut(stdout);
		}
		String answer[] = output.toString().split("\r\n");
		String state = answer[answer.length-3];
		if(answer.length == 16){
			Assert.assertEquals(answer[6], result);
		}
		else{
			Assert.assertEquals(answer[answer.length-3], result);
		}
	}
}
