package automationTesting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class signUpTest
{
	public static void SignUp() throws IOException, InterruptedException
	{
		System.setProperty("webdriver.gecko.driver","geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/AngularJs/");
		driver.findElement(By.id("join")).click();
		Thread.sleep(500);
		driver.findElement(By.id("signUp")).click();
		String url=driver.getCurrentUrl();
		FileInputStream fileStream=new FileInputStream(new File("signUpData.xlsx"));
		XSSFWorkbook workBook=new XSSFWorkbook(fileStream);
		XSSFSheet sheet=workBook.getSheetAt(0);
		int numOfRows=sheet.getLastRowNum();
		WebElement dropDownListBox;
		Select clickThis;
		String name,email,gender,password,confirmPassword,type,country;
		Date birthdate = null;
		SimpleDateFormat simpleFormat = new SimpleDateFormat("MM-dd-yyyy");
		for(int row=0;row<=numOfRows;row++)
		{
			if(sheet.getRow(row).getCell(0).getStringCellValue()!=null)
			{
				name=sheet.getRow(row).getCell(0).getStringCellValue();
			}
			else
			{
				name="";
			}
			if(sheet.getRow(row).getCell(1).getStringCellValue()!=null)
			{
				email=sheet.getRow(row).getCell(1).getStringCellValue();
			}
			else
			{
				email="";
			}
			if(sheet.getRow(row).getCell(2).getStringCellValue()!=null)
			{
				gender=sheet.getRow(row).getCell(2).getStringCellValue();
			}
			else
			{
				gender="";
			}
			if(sheet.getRow(row).getCell(3).getStringCellValue()!=null)
			{
				password=sheet.getRow(row).getCell(3).getStringCellValue();
			}
			else
			{
				password="";
			}
			if(sheet.getRow(row).getCell(4).getStringCellValue()!=null)
			{
				confirmPassword=sheet.getRow(row).getCell(4).getStringCellValue();
			}
			else
			{
				confirmPassword="";
			}
			if(sheet.getRow(row).getCell(5).getStringCellValue()!=null)
			{
				type=sheet.getRow(row).getCell(5).getStringCellValue();
			}
			else
			{
				type="";
			}
			if(sheet.getRow(row).getCell(6).getStringCellValue()!=null)
			{
				country=sheet.getRow(row).getCell(6).getStringCellValue();
			}
			else
			{
				country="";
			}
			if(sheet.getRow(row).getCell(7).getDateCellValue()!=null)
			{
				birthdate=sheet.getRow(row).getCell(7).getDateCellValue();
			}
			else
			{
				birthdate=null;
			}
			
			driver.findElement(By.id("userName")).clear();
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("male")).click();
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("confirmPassword")).clear();
			driver.findElement(By.id("student")).click();
			dropDownListBox = driver.findElement(By.name("country"));
			clickThis = new Select(dropDownListBox);
			clickThis.selectByVisibleText("Egypt");
			((JavascriptExecutor)driver).executeScript 
			("document.getElementById('date').removeAttribute('readonly',0);");
			WebElement BirthDate= driver.findElement(By.id("date"));
			BirthDate.clear();
			
			driver.findElement(By.id("userName")).sendKeys(name);
			driver.findElement(By.id("email")).sendKeys(email);
			
			if(gender.equals("male"))
			{
				driver.findElement(By.id("male")).click();
			}
			else if(gender.equals("female"))
			{
				driver.findElement(By.id("female")).click();
			}
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("confirmPassword")).sendKeys(confirmPassword);
			
			if(type.equals("teacher"))
			{
				driver.findElement(By.id("teacher")).click();
			}
			else if(type.equals("student"))
			{
				driver.findElement(By.id("student")).click();
			}
			if(!country.equals(""))
			{
				dropDownListBox = driver.findElement(By.name("country"));
				clickThis = new Select(dropDownListBox);
				clickThis.selectByVisibleText(country);
			}
			if(birthdate!=null)
			{
				BirthDate.sendKeys(simpleFormat.format(birthdate));
			}
			else if(birthdate==null)
			{
				BirthDate.sendKeys("");
			}
			driver.findElement(By.id("SignUp")).click();
			String currentUrl=driver.getCurrentUrl();
			Thread.sleep(5000);
			if(!currentUrl.equals(url))
			{
				driver.navigate().back();
			}
		}
		fileStream.close();
		workBook.close();
		driver.close();
	}
	public static void main(String []args) throws IOException, InterruptedException
	{
		SignUp();
	}

}
