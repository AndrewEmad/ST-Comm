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

public class createCourseTest
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
	public static void createCouse() throws InterruptedException, IOException {
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/AngularJs/");
		FileInputStream fileStream = new FileInputStream(new File("createCourse.xlsx"));
		XSSFWorkbook workBook = new XSSFWorkbook(fileStream);
		XSSFSheet sheet = workBook.getSheetAt(0);
		int numOfRows = sheet.getLastRowNum();
		String name, password, courseName;

		for (int row = 0; row <=numOfRows; row++) {
			Thread.sleep(2000);
			driver.findElement(By.id("join")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("signIn")).click();
			Thread.sleep(1000);
			name = sheet.getRow(row).getCell(0).getStringCellValue();
			password = sheet.getRow(row).getCell(1).getStringCellValue();
			driver.findElement(By.id("userName")).clear();
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("userName")).sendKeys(name);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("SignIn")).click();
			int numColumns = sheet.getRow(row).getLastCellNum();
			Thread.sleep(9000);
			for (int column = 2; column < numColumns; column++) {
				Thread.sleep(1000);
				driver.findElement(By.id("createCourse")).click();
				if (sheet.getRow(row).getCell(column).getStringCellValue() != null) 
				{
					courseName = sheet.getRow(row).getCell(column).getStringCellValue();
				}
				else 
				{
					courseName = "";
				}
				Thread.sleep(1000);
				driver.findElement(By.id("newCourse")).clear();
				driver.findElement(By.id("newCourse")).sendKeys(courseName);
				driver.findElement(By.id("addCourse")).click();
				Thread.sleep(2000);
				if(existsElement(driver,"cancelCreateCourse")==true
					&&driver.findElement(By.id("cancelCreateCourse")).isDisplayed())
				{
					Thread.sleep(1000);
					driver.findElement(By.id("cancelCreateCourse")).click();
				}
			}
			Thread.sleep(1000);
			driver.findElement(By.id("signOut")).click();
			Thread.sleep(2000);
		}
		fileStream.close();
		workBook.close();
		driver.close();
	}
	public static void main(String[] args) throws InterruptedException, IOException {
		createCouse();
	}

}
