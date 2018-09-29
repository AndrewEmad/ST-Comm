package automatedTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TrackGameChanges
{
	public void trackGameChanges() throws IOException, InterruptedException
	{
		System.setProperty("webdriver.gecko.driver","geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/AngularJs/");
		FileInputStream fileStream=new FileInputStream(new File("trackChanges.xlsx"));
		XSSFWorkbook workBook=new XSSFWorkbook(fileStream);
		XSSFSheet sheet=workBook.getSheetAt(0);
		int numOfRows=sheet.getLastRowNum();
		String name,password,courseName,undoTrackNumber;
		
		driver.findElement(By.id("join"));
		Thread.sleep(5000);
		driver.findElement(By.id("signIn")).click();
		Thread.sleep(5000);
		name=sheet.getRow(0).getCell(0).getStringCellValue();
		password=sheet.getRow(0).getCell(1).getStringCellValue();
		driver.findElement(By.id("userName")).sendKeys(name);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("SignIn")).click();
		Thread.sleep(6000);
		for(int row=1;row<numOfRows;row++)
		{
			courseName=sheet.getRow(row).getCell(0).getStringCellValue();
			driver.findElement(By.id(courseName)).click();
			undoTrackNumber=sheet.getRow(row).getCell(1).getStringCellValue();
			if(undoTrackNumber.equals(null)||undoTrackNumber.length()<1)
			{
				undoTrackNumber="";
			}
			Thread.sleep(5000);
			driver.findElement(By.id("Log")).click();
			Thread.sleep(5000);
			driver.findElement(By.id(undoTrackNumber)).click();
			Thread.sleep(5000);
			driver.findElement(By.id("home")).click();
			Thread.sleep(5000);
		}
		Thread.sleep(5000);
		driver.findElement(By.id("signOut")).click();
		Thread.sleep(5000);
		
		fileStream.close();
		workBook.close();
		driver.close();
	}
}
