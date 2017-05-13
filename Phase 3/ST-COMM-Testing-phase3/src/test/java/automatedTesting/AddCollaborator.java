package automatedTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddCollaborator
{
	public void addCollaborator() throws IOException, InterruptedException
	{
		System.setProperty("webdriver.gecko.driver","geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		FileInputStream fileStream=new FileInputStream(new File("addCollaborator.xlsx"));
		XSSFWorkbook workBook=new XSSFWorkbook(fileStream);
		XSSFSheet sheet=workBook.getSheetAt(0);
		int numOfRows=sheet.getLastRowNum();
		String teacherName=sheet.getRow(0).getCell(0).getStringCellValue();
		String password=sheet.getRow(0).getCell(1).getStringCellValue();
		
		driver.findElement(By.id("join"));
		Thread.sleep(15000);
		driver.findElement(By.id("signIn")).click();
		Thread.sleep(15000);
		driver.findElement(By.id("userName")).sendKeys(teacherName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("SignIn")).click();
		Thread.sleep(15000);
		String courseName,gameName,collaboratorName;
		for(int row=1;row<numOfRows;row++)
		{
			courseName=sheet.getRow(row).getCell(0).getStringCellValue();
			gameName=sheet.getRow(row).getCell(1).getStringCellValue();
			collaboratorName=sheet.getRow(0).getCell(2).getStringCellValue();
			
			driver.findElement(By.id(courseName)).click();
			Thread.sleep(15000);
			//driver.findElement(By.id(gameName)).click();
			//Thread.sleep(15000);
			driver.findElement(By.id("Menu")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("getCollForm")).click();
			Thread.sleep(15000);
			if(collaboratorName==null||collaboratorName.length()<=1)
				collaboratorName="";
			driver.findElement(By.id("collName")).sendKeys(collaboratorName);
			Thread.sleep(1000);
			driver.findElement(By.id("addColl")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("cancelAdding")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("home")).click();
			Thread.sleep(15000);
		}
		
		
		
		fileStream.close();
		workBook.close();
		driver.close();
	}

}
