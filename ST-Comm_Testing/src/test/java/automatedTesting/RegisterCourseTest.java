package automatedTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RegisterCourseTest
{
	public static void registerCourse() throws InterruptedException, IOException
	{

		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/AngularJs/");
		
		FileInputStream fileStream=new FileInputStream(new File("RegisterCourse.xlsx"));
		XSSFWorkbook workBook=new XSSFWorkbook(fileStream);
		XSSFSheet sheet=workBook.getSheetAt(0);
		int numOfRows=sheet.getLastRowNum();
		String name,password,courseName;
		for(int row=0;row<=numOfRows;row++)
		{
			driver.findElement(By.id("join")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("signIn")).click();
			name=sheet.getRow(row).getCell(0).getStringCellValue();
			password=sheet.getRow(row).getCell(1).getStringCellValue();
			driver.findElement(By.id("userName")).clear();
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("userName")).sendKeys(name);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("SignIn")).click();
			Thread.sleep(9000);
			driver.findElement(By.id("allCourses")).click();
			int numColumns=sheet.getRow(row).getLastCellNum();
			for(int column=2;column<numColumns;column++)
			{
				if(sheet.getRow(row).getCell(column).getStringCellValue()!="")
				{
					courseName=sheet.getRow(row).getCell(column).getStringCellValue();
					Thread.sleep(2000);
					driver.findElement(By.id(courseName)).click();
				}
			}
			Thread.sleep(5000);
		    driver.findElement(By.id("signOut")).click();
		    Thread.sleep(2000);
		}
		fileStream.close();
		workBook.close();
		driver.close();
	}
	public static void main(String[]args) throws InterruptedException, IOException
	{
		registerCourse();
	}

}
