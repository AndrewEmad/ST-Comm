package automatedTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class playGameTest 
{
	public static void playGame() throws IOException, InterruptedException
	{
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/AngularJs/");
		
		FileInputStream fileStream=new FileInputStream(new File("playGame.xlsx"));
		XSSFWorkbook workBook=new XSSFWorkbook(fileStream);
		XSSFSheet sheet=workBook.getSheetAt(0);
		int numOfRows=sheet.getLastRowNum();
		
		String name,password;
		String courseName,gameName;
		for(int row=0;row<numOfRows;row++)
		{
			driver.findElement(By.id("join")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("signIn")).click();
			Thread.sleep(2000);
			name=sheet.getRow(row).getCell(0).getStringCellValue();
			password=sheet.getRow(row).getCell(1).getStringCellValue();
			driver.findElement(By.id("userName")).clear();
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("userName")).sendKeys(name);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("SignIn")).click();
			Thread.sleep(15000);
			courseName=sheet.getRow(row).getCell(2).getStringCellValue();
			driver.findElement(By.id(courseName)).click();
			Thread.sleep(15000);
			gameName=sheet.getRow(row).getCell(3).getStringCellValue();
			driver.findElement(By.name(gameName)).click();
			Thread.sleep(15000);
			int numColumns=sheet.getRow(row).getLastCellNum();
			for(int column=4;column<numColumns;column++)
			{
				if(sheet.getRow(row).getCell(column).getStringCellValue()!=null)
				{
					Thread.sleep(6000);
					String choise=sheet.getRow(row).getCell(column).getStringCellValue();
					driver.findElement(By.id(choise)).click();
				}
			    Thread.sleep(2000);
				driver.findElement(By.id("submitAnswer")).click();
			    Thread.sleep(3000);
			}
			Thread.sleep(3000);
			driver.findElement(By.id("closeGame")).click();
		    Thread.sleep(3000);
			driver.findElement(By.id("signOut")).click();
		    Thread.sleep(2000);
		}
		fileStream.close();
		workBook.close();
		driver.close();
	}
	public static void main(String[]args) throws IOException, InterruptedException
	{
		playGame();
	}

}
