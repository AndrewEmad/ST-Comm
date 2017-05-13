package automatedTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EditGame 
{
	public void editGame() throws IOException, InterruptedException
	{
		System.setProperty("webdriver.gecko.driver","geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		FileInputStream fileStream=new FileInputStream(new File("editGame.xlsx"));
		XSSFWorkbook workBook=new XSSFWorkbook(fileStream);
		XSSFSheet sheet=workBook.getSheetAt(0);
		int numOfRows=sheet.getLastRowNum();
		driver.findElement(By.id("join"));
		Thread.sleep(15000);
		driver.findElement(By.id("signIn")).click();
		Thread.sleep(15000);
		String name=sheet.getRow(0).getCell(0).getStringCellValue();
		String password=sheet.getRow(0).getCell(1).getStringCellValue();
		
		driver.findElement(By.id("userName")).sendKeys(name);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("SignIn")).click();
		Thread.sleep(15000);
		
		for(int row=1;row<numOfRows;row++)
		{
			String courseName=sheet.getRow(row).getCell(0).getStringCellValue();
			driver.findElement(By.id(courseName)).click();
			
			Thread.sleep(15000);
			String gameName=sheet.getRow(row).getCell(1).getStringCellValue();
			driver.findElement(By.name(gameName)).click();
			Thread.sleep(15000);
			driver.findElement(By.id("Menu")).click();
			Thread.sleep(15000);
			driver.findElement(By.id("editGame")).click();
			Thread.sleep(15000);
			
			// here add new question or delete existing question
			
			
			
			Thread.sleep(15000);
			driver.findElement(By.id("home")).click();
			Thread.sleep(15000);
		}
		
		Thread.sleep(15000);
		driver.findElement(By.id("signOut")).click();
		Thread.sleep(15000);
		
		
		
		
		
		fileStream.close();
		workBook.close();
		driver.close();
	}
}
