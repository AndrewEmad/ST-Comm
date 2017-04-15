package automationTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class createCourseTest 
{
	public static void createCouse() throws InterruptedException, IOException
	{
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///C://Users//Mariam Ashraf//Desktop//AngularJs//WebContent//index.html");
		
		FileInputStream fileStream=new FileInputStream(new File("logInDataAndCreateCourse.xlsx"));
		XSSFWorkbook workBook=new XSSFWorkbook(fileStream);
		XSSFSheet sheet=workBook.getSheetAt(0);
		int numOfRows=sheet.getLastRowNum();
		String name,password,courseName;
		
		for(int row=0;row<numOfRows;row++)
		{
			driver.findElement(By.id("join")).click();
			Thread.sleep(500);
			driver.findElement(By.id("signIn")).click();
			
			name=sheet.getRow(row).getCell(0).getStringCellValue();
			password=sheet.getRow(row).getCell(1).getStringCellValue();
			driver.findElement(By.id("userName")).clear();
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("userName")).sendKeys(name);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("SignIn")).click();
			Thread.sleep(500);
	
			int numColumns=sheet.getRow(row).getLastCellNum();
			System.out.println(numColumns);
			for(int column=2;column<numColumns;column++)
			{
				if(sheet.getRow(row).getCell(column).getStringCellValue()!=null)
				{
					courseName=sheet.getRow(row).getCell(column).getStringCellValue();
				}
				else
				{
					courseName="";
				}
				String url=driver.getCurrentUrl();
				driver.findElement(By.id("createCourse")).click();
				driver.findElement(By.id("newCourse")).clear();
				driver.findElement(By.id("newCourse")).sendKeys(courseName);
				driver.findElement(By.id("addCourse")).click();
				Thread.sleep(2000);
				String currentUrl=driver.getCurrentUrl();
				if(!currentUrl.equals(url))
				{
					driver.navigate().back();
				}
			}
			driver.findElement(By.id("signOut")).click();
			Thread.sleep(500);
		}
		fileStream.close();
		workBook.close();
		driver.close();
	}
	public static void main(String[]args) throws InterruptedException, IOException
	{
		createCouse();
	}
}
