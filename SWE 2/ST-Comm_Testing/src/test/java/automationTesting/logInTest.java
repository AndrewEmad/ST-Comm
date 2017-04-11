package automationTesting;

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
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Mariam Ashraf\\Downloads\\geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///C://Users//Mariam Ashraf//Desktop//AngularJs//WebContent//index.html");
		driver.findElement(By.id("join")).click();
		//driver.findElement(By.id("signIn")).click();
		String url=driver.getCurrentUrl();
		
		FileInputStream fileStream=new FileInputStream(new File("C:\\Users\\Mariam Ashraf\\Documents\\eclipse\\projects\\ST-Comm_Testing\\logInData.xlsx"));
		XSSFWorkbook workBook=new XSSFWorkbook(fileStream);
		XSSFSheet sheet=workBook.getSheetAt(0);
		int numOfRows=sheet.getLastRowNum();
		for(int row=0;row<=numOfRows;row++)
		{
			String name=sheet.getRow(row).getCell(0).getStringCellValue();
			String password=sheet.getRow(row).getCell(1).getStringCellValue();
			if(name.equals("'"))
				name="";
			if(password.equals("'"))
				password="";
			driver.findElement(By.id("userName")).clear();
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("userName")).sendKeys(name);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("SignIn")).click();
			String currentUrl=driver.getCurrentUrl();
			Thread.sleep(3000);
			if(!currentUrl.equals(url))
			{
				driver.findElement(By.id("signOut")).click();
				driver.findElement(By.id("join")).click();
			//	driver.findElement(By.id("signIn")).click();
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
