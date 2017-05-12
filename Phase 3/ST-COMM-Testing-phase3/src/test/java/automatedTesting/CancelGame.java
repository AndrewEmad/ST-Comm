package automatedTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CancelGame 
{
	public void cancelGame() throws InterruptedException, IOException
	{
		System.setProperty("webdriver.gecko.driver","geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		FileInputStream fileStream=new FileInputStream(new File("cancelGame.xlsx"));
		XSSFWorkbook workBook=new XSSFWorkbook(fileStream);
		XSSFSheet sheet=workBook.getSheetAt(0);
		int numOfRows=sheet.getLastRowNum();
		String name,password;
		String courseName,cancelGame,gameName;
		for(int row=0;row<numOfRows;row++)
		{
			driver.findElement(By.id("join"));
			Thread.sleep(15000);
			driver.findElement(By.id("signIn")).click();
			Thread.sleep(15000);
			name=sheet.getRow(row).getCell(0).getStringCellValue();
			password=sheet.getRow(row).getCell(1).getStringCellValue();
			driver.findElement(By.id("userName")).sendKeys(name);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("SignIn")).click();
			
			Thread.sleep(15000);
			courseName=sheet.getRow(row).getCell(2).getStringCellValue();
			
			driver.findElement(By.id(courseName)).click();
			Thread.sleep(15000);
			
			cancelGame=sheet.getRow(row).getCell(3).getStringCellValue();
			
			if(cancelGame.equals("cancel"))
			{
				gameName=sheet.getRow(row).getCell(4).getStringCellValue();
				
				driver.findElement(By.name(gameName)).click();
				Thread.sleep(15000);
				driver.findElement(By.id("Menu")).click();
				Thread.sleep(15000);
				driver.findElement(By.id("cancelGame")).click();
				Thread.sleep(15000);
			}
			
			Thread.sleep(15000);
			driver.findElement(By.id("signOut")).click();
			Thread.sleep(15000);
		}
		
		fileStream.close();
		workBook.close();
		driver.close();
		
	}

}
