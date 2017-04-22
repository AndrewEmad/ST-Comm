package automatedTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class logInTest 
{
	public static void LogIn() throws IOException, InterruptedException
	{
		System.setProperty("webdriver.gecko.driver","geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/AngularJs/");
		driver.findElement(By.id("join")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("signIn")).click();
		Thread.sleep(2000);
		String url=driver.getCurrentUrl();
		
		FileInputStream fileStream=new FileInputStream(new File("logInData.xlsx"));
		XSSFWorkbook workBook=new XSSFWorkbook(fileStream);
		XSSFSheet sheet=workBook.getSheetAt(0);
		int numOfRows=sheet.getLastRowNum();
		String name,password;
		for(int row=0;row<=numOfRows;row++)
		{
			if(sheet.getRow(row).getCell(0).getStringCellValue()!=null)
				name=sheet.getRow(row).getCell(0).getStringCellValue();
			else
				name="";
			if(sheet.getRow(row).getCell(1).getStringCellValue()!=null)
				password=sheet.getRow(row).getCell(1).getStringCellValue();
			else
				password="";
			driver.findElement(By.id("userName")).clear();
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("userName")).sendKeys(name);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("SignIn")).click();
			String currentUrl=driver.getCurrentUrl();
			Thread.sleep(5000);
			if(!currentUrl.equals(url))
			{
				driver.findElement(By.id("signOut")).click();
				driver.findElement(By.id("join")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("signIn")).click();
			}
		}
		fileStream.close();
		workBook.close();
		driver.close();
	}
	public static void main(String []args) throws IOException, InterruptedException
	{
		LogIn();
	}
}
