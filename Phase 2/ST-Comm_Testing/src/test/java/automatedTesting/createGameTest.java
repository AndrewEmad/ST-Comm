package automatedTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class createGameTest 
{
	private static boolean existsElement(WebDriver driver,String id) {
	    try 
	    {
	        driver.findElement(By.id(id));
		    return true;
	    }
	    catch (NoSuchElementException e)
	    {
	        return false;
	    }
	}
	public static void createGame() throws IOException, InterruptedException
	{
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/AngularJs/");

		FileInputStream fileStream=new FileInputStream(new File("CreateGame.xlsx"));
		XSSFWorkbook workBook=new XSSFWorkbook(fileStream);
		XSSFSheet sheet=workBook.getSheetAt(0);
		int numOfRows=sheet.getLastRowNum();
		String name,password;
		String courseName,gameName;
		int numOfQuestions;
		for(int row=0;row<numOfRows;row++)
		{
			Thread.sleep(30000);
			driver.findElement(By.id("join")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("signIn")).click();

			name=sheet.getRow(row).getCell(0).getStringCellValue();
			password=sheet.getRow(row).getCell(1).getStringCellValue();
			driver.findElement(By.id("userName")).clear();
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("userName")).sendKeys(name);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("SignIn")).click();
			Thread.sleep(9000);
	
			driver.findElement(By.id("createGame")).click();
			Thread.sleep(6000);
			if(sheet.getRow(row).getCell(2).getStringCellValue()!=null)
			{
				courseName=sheet.getRow(row).getCell(2).getStringCellValue();
			}
			else
			{
				courseName="";
			}
			if(sheet.getRow(row).getCell(3).getStringCellValue()!=null)
			{
				gameName=sheet.getRow(row).getCell(3).getStringCellValue();
			}
			else
			{
				gameName="";
			}
			Thread.sleep(2000);
			numOfQuestions=(int) sheet.getRow(row).getCell(4).getNumericCellValue();
			driver.findElement(By.id("courseName")).clear();
			driver.findElement(By.id("gameName")).clear();
			driver.findElement(By.id("numOfQuestions")).clear();
			driver.findElement(By.id("courseName")).sendKeys(courseName);
			driver.findElement(By.id("gameName")).sendKeys(gameName);
			driver.findElement(By.id("numOfQuestions")).sendKeys(Integer.toString((numOfQuestions)));
			Thread.sleep(2000);
			driver.findElement(By.id("getQuestionform")).click();
			Thread.sleep(7000);
			if(sheet.getRow(row).getCell(5).getStringCellValue().equals("True/False")&&(existsElement(driver,"tf")==true))
			{
				String qStatment,correctAnswer = "";
				for(int numQuestions=1;numQuestions<=numOfQuestions;numQuestions+=2)
				{
					if(sheet.getRow(row).getCell(5+numQuestions).getStringCellValue()!=null)
					{
						qStatment=sheet.getRow(row).getCell(5+numQuestions).getStringCellValue();
					}
					else
					{
						qStatment="";
					}
					if(sheet.getRow(row).getCell(5+numQuestions+1).getStringCellValue()!=null)
					{
						correctAnswer=sheet.getRow(row).getCell(5+numQuestions+1).getStringCellValue();
					}
					if(driver.findElement(By.id("Qstatement")).isDisplayed())
					{driver.findElement(By.id("Qstatement")).clear();
					driver.findElement(By.id("Qstatement")).sendKeys(qStatment);
					driver.findElement(By.id("tf")).click();
					driver.findElement(By.id("answer")).clear();
					driver.findElement(By.id("mcq1")).sendKeys("true");
					driver.findElement(By.id("mcq2")).sendKeys("false");
					driver.findElement(By.id("answer")).sendKeys(correctAnswer);
					Thread.sleep(2000);
					driver.findElement(By.id("submitQuestion")).click();
					Thread.sleep(2000);
					}
				}
			}
			else if(sheet.getRow(row).getCell(5).getStringCellValue().equals("MCQ")&&(existsElement(driver,"mcq")==true))
			{
				String qStatment;
				int nextCellNum=5;
				for(int numQuestions=1;numQuestions<=numOfQuestions;numQuestions++)
				{
					nextCellNum++;
					if(sheet.getRow(row).getCell(nextCellNum).getStringCellValue()!=null)
					{
						qStatment=sheet.getRow(row).getCell(nextCellNum).getStringCellValue();
					}
					else
					{
						qStatment="";
					}
					int numOfAnswers=4;
					String[]answers = new String[numOfAnswers];
					String correctAnswer;
					for(int answer=1;answer<=numOfAnswers;answer++)
					{
						nextCellNum++;
						if(sheet.getRow(row).getCell(nextCellNum).getStringCellValue()!=null)
						{
							answers[answer-1]=sheet.getRow(row).getCell(nextCellNum).getStringCellValue();
						}
						else
						{
							answers[answer-1]="";
						}
					}
					nextCellNum++;
					correctAnswer=sheet.getRow(row).getCell(nextCellNum).getStringCellValue();
					driver.findElement(By.id("Qstatement")).clear();
					driver.findElement(By.id("Qstatement")).sendKeys(qStatment);
					driver.findElement(By.id("mcq")).click();

					for(int answer=1;answer<=numOfAnswers;answer++)
					{
						driver.findElement(By.id("mcq"+Integer.toString(answer))).clear();
					}
					driver.findElement(By.id("answer")).clear();
					for(int answer=1;answer<=numOfAnswers;answer++)
					{
						driver.findElement(By.id("mcq"+Integer.toString(answer))).sendKeys(answers[answer-1]);
					}
					driver.findElement(By.id("answer")).sendKeys(correctAnswer);
					Thread.sleep(5000);
					driver.findElement(By.id("submitQuestion")).click();
				}
			}
			if(driver.findElement(By.id("cancelCreateGame")).isDisplayed())
			{
				driver.findElement(By.id("cancelCreateGame")).click();
			}
			Thread.sleep(5000);
			driver.findElement(By.id("signOut")).click();
			Thread.sleep(3000);
		}
		fileStream.close();
		workBook.close();
		driver.close();
	}
	public static void main(String[]args) throws IOException, InterruptedException
	{
		createGame();
	}

}
