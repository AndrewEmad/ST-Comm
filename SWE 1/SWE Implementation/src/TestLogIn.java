
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestLogIn {
	@DataProvider(name = "DATA")
	public static Object[][] LogInStudent() {
		return new Object[][] {
				// Student
				{ 1, "\r\n\r\nsss\r\nd\r\n2", "Sign in Faild" },
				{ 1, "sss\r\n\r\nsss\r\nd\r\n2", "Sign in Faild" },
				{ 1, "\r\nd\r\nsss\r\nd\r\n2", "Sign in Faild" },
				{ 1, "sss\r\nLLL\r\nsss\r\nd\r\n2", "Sign in Faild" }, 
				{ 1, "sss\r\nd\r\n2", "Signing in Successful" }, 
				{ 1, "hoda\r\n1234\r\nsss\r\nd\r\n2", "Sign in Faild" }, 

				// Teacher
				{ 2, "\r\n\r\nsss\r\nd\r\n2", "Sign in Faild" },
				{ 2, "sss\r\n\r\nsss\r\nd\r\n2", "Sign in Faild" },
				{ 2, "\r\nd\r\nsss\r\nd\r\n2", "Sign in Faild" },
				{ 2, "sss\r\nLLL\r\nsss\r\nd\r\n2", "Sign in Faild" }, 
				{ 2, "sss\r\nd\r\n2", "Sign in Faild" }, //This data for student not teacher
				{ 2, "hoda\r\n1234\r\nsss\r\nd\r\n2", "Sign in Faild" }
		};
	}

	@Test(dataProvider = "DATA")
	public static void loginTest(int kind, String input, String result) {
		InputStream stdin = System.in;
		PrintStream stdout = System.out;
		System.setIn(new ByteArrayInputStream(input.getBytes()));
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		Interfacee i = new Interfacee();
		try {
			i.SigninForm(kind);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.setIn(stdin);
			System.setOut(stdout);
		}

		String answer[] = output.toString().split("\r\n");
		
		Assert.assertEquals(answer[2], result);
	}

}
