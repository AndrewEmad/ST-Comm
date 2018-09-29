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
	public void editGame() throws IOException, InterruptedException {
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/AngularJs/");
		FileInputStream fileStream = new FileInputStream(new File("editGame.xlsx"));
		XSSFWorkbook workBook = new XSSFWorkbook(fileStream);
		XSSFSheet sheet = workBook.getSheetAt(0);
		int numOfRows = sheet.getLastRowNum();
		driver.findElement(By.id("join"));
		Thread.sleep(15000);
		driver.findElement(By.id("signIn")).click();
		Thread.sleep(15000);
		String name = sheet.getRow(0).getCell(0).getStringCellValue();
		String password = sheet.getRow(0).getCell(1).getStringCellValue();

		driver.findElement(By.id("userName")).sendKeys(name);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("SignIn")).click();
		Thread.sleep(15000);

		for (int row = 1; row < numOfRows; row++) {
			String courseName = sheet.getRow(row).getCell(0).getStringCellValue();
			driver.findElement(By.id(courseName)).click();

			Thread.sleep(15000);
			String gameName = sheet.getRow(row).getCell(1).getStringCellValue();
			driver.findElement(By.name(gameName)).click();
			Thread.sleep(15000);
			driver.findElement(By.id("Menu")).click();
			Thread.sleep(15000);
			driver.findElement(By.id("editGame")).click();
			Thread.sleep(15000);

			String newGameName = sheet.getRow(row).getCell(2).getStringCellValue();
			String numofQuestions = sheet.getRow(row).getCell(3).getStringCellValue();
			if (newGameName.equals(null) || newGameName.length() <= 1) {
				newGameName = "";
			}
			driver.findElement(By.id("gameName2")).sendKeys(newGameName);
			driver.findElement(By.id("numOfQuestions2")).sendKeys(numofQuestions);
			Thread.sleep(3000);
			driver.findElement(By.id("editQuestionform")).click();
			if (newGameName == gameName || newGameName.equals("")) {
				driver.findElement(By.id("cancelEditingGame")).click();
			} else {
				int columnNum = 4;
				String qStatment, correctAnswer, timer;
				if (sheet.getRow(row).getCell(columnNum).getStringCellValue().equals("T/F")) {
					columnNum++;
					for (int numquestion = 1; numquestion <= Integer.parseInt(numofQuestions); numquestion++) {
						if (sheet.getRow(row).getCell(columnNum).getStringCellValue() != null) {
							qStatment = sheet.getRow(row).getCell(columnNum).getStringCellValue();
						} else {
							qStatment = "";
						}
						columnNum++;
						if (sheet.getRow(row).getCell(columnNum).getStringCellValue() != null) {
							correctAnswer = sheet.getRow(row).getCell(columnNum).getStringCellValue();
						} else {
							correctAnswer = "";
						}
						columnNum++;
						timer = sheet.getRow(row).getCell(columnNum).getStringCellValue();
						columnNum++;
						driver.findElement(By.id("Qstatement2")).sendKeys(qStatment);
						driver.findElement(By.id("tf2")).click();
						driver.findElement(By.id("mcq12")).sendKeys("True");
						driver.findElement(By.id("mcq22")).sendKeys("False");
						driver.findElement(By.id("answer2")).sendKeys(correctAnswer);
						driver.findElement(By.id("time2")).sendKeys(timer);
						Thread.sleep(5000);
						driver.findElement(By.id("editQuestion")).click();
						Thread.sleep(6000);
					}
				} else if (sheet.getRow(row).getCell(columnNum).getStringCellValue().equals("MCQ")) {
					columnNum++;
					String choice[] = new String[4];
					for (int numQuestion = 1; numQuestion <= Integer.parseInt(numofQuestions); numQuestion++) {
						if (sheet.getRow(row).getCell(columnNum).getStringCellValue() != null)
							qStatment = sheet.getRow(row).getCell(columnNum).getStringCellValue();
						else
							qStatment = "";

						columnNum++;
						for (int numAnswer = 1; numAnswer <= 4; numAnswer++) {
							if (sheet.getRow(row).getCell(columnNum).getStringCellValue() != null)
								choice[numAnswer - 1] = sheet.getRow(row).getCell(columnNum).getStringCellValue();
							else
								choice[numAnswer - 1] = "";

							columnNum++;
						}
						if (sheet.getRow(row).getCell(columnNum).getStringCellValue() != null)
							correctAnswer = sheet.getRow(row).getCell(columnNum).getStringCellValue();
						else
							correctAnswer = "";
						columnNum++;
						timer = sheet.getRow(row).getCell(columnNum).getStringCellValue();
						columnNum++;
						driver.findElement(By.id("Qstatement2")).sendKeys(qStatment);
						for (int choices = 1; choices <= 4; choices++) {
							driver.findElement(By.id("mcq" + choices + "2")).sendKeys(choice[choices - 1]);
						}
						driver.findElement(By.id("answer2")).sendKeys(correctAnswer);
						driver.findElement(By.id("time2")).sendKeys(timer);
						driver.findElement(By.id("editQuestion")).click();
						Thread.sleep(6000);
					}
				}
			}
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
