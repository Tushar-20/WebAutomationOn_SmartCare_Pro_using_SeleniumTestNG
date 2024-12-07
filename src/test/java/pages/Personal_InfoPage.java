package pages;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import basedriver.PageDriver;
import utilities.ExcelUtils;
import utilities.GetScreenShot;

public class Personal_InfoPage {
	ExtentTest test;
	

	
	public Personal_InfoPage(ExtentTest test) {
		PageFactory.initElements(PageDriver.getCurrentDriver(),this);
		this.test = test;
	}
	
	@FindBys ({
		@FindBy(xpath="//body/div[@id='root']/div[2]/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/input[1]"),
	     @FindBy(xpath=("/html[1]/body[1]/div[1]/div[2]/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/input[1]"))
	})
	WebElement First_Name;
	
	@FindBy(xpath= ("//body/div[@id='root']/div[2]/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[2]/input[1]"))
	WebElement Surname;
	
	@FindBy(xpath=("//input[@placeholder='dd-mm-yyyy']"))
	WebElement DateOFBirth;
	@FindBy(xpath=("//select[@class='react-datepicker__month-select']"))
	WebElement Month;
	@FindBy(xpath=("//select[@class='react-datepicker__year-select']"))
	WebElement Year;
	@FindBy(xpath=("//div[@aria-label='Choose Monday, December 3rd, 2001']"))
	WebElement Day;
	
	@FindBy(xpath="//body/div[@id='root']/div[2]/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[4]/select[1]")
	WebElement Sex;
	
	@FindBy(xpath="//body/div[@id='root']/div[2]/div[2]/div[2]/form[1]/div[1]/div[2]/div[1]/div[5]/div[1]/input[1]")
	WebElement Designation;
	
	@FindBy(xpath="//p[contains(text(),'I do not have NRC')]")
	WebElement NRC;
	
	@FindBy(xpath="//button[contains(text(),'Next')]")
	WebElement NextButton;
	
	public void failCase(String message, String scName) throws IOException {
		test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>"+message+"</b></p>");
		Throwable t = new InterruptedException("Exception");
		test.fail(t);
		String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDriver(), ""+scName+"");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + ""+scName+".png";
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
		PageDriver.getCurrentDriver().quit();
	}
	
	
	public void passCase(String message) {
		test.pass("<p style=\"color:#FF5353; font-size:13px\"><b>"+message+"</b></p>");
	}
	
	
	public void passCaseWithSc(String message, String scName) throws IOException  {
		test.pass("<p style=\"color:#FF5353; font-size:13px\"><b>"+message+"</b></p>");
		String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDriver(), ""+scName+"");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + ""+scName+".png";
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
	}
	
	public void Personal_info() throws InterruptedException, IOException {
		try {
			test.info("Please enter First_Name");
			if(First_Name.isDisplayed()) {
				First_Name.sendKeys("Tushar Chandra");
				Thread.sleep(3000);
				passCase("First_Name entered");
			}
			try {
				test.info("Please enter Surname");
				if(Surname.isDisplayed()) {
					Surname.sendKeys("Sarker");
					Thread.sleep(3000);
					passCase("Surname entered");
				}try {
					test.info("Please enter Date of Birth");
					if(DateOFBirth.isDisplayed()) {
						DateOFBirth.click();
						 Thread.sleep(5000);
						//DateOFBirth.click();
						 
						Month.click();
						Select selectmonth = new Select(Month);						
						selectmonth.selectByVisibleText("December");;
			            Thread.sleep(5000);
			            
			            Year.click();
			            Select selectyear = new Select(Year);
			            selectyear.selectByVisibleText("2001");;
			            Thread.sleep(5000);
			            
			           
						Day.click();
			            Thread.sleep(3000);
					
					}
					try {
						test.info("Please enter Your Sex");
						if(Sex.isDisplayed()) {
						Select selectSex = new Select(Sex);
			            selectSex.selectByVisibleText("Male");
			            Thread.sleep(3000);
					}
				try {
					test.info("Please enter Designation");
					if(Designation.isDisplayed()) {
						Designation.sendKeys("Manager");
						passCase("Designation entered");
						Thread.sleep(3000);
					}
					try {
						if(NRC.isDisplayed()) {
						NRC.click();
						}
					try {
						test.info("Click on the Next");
						if(NextButton.isDisplayed()) {
							NextButton.click();
							Thread.sleep(3000);
							passCaseWithSc("Next Button click Successful","NextPass.");
						}
					}catch(Exception e) {
			failCase("First name was not lacatable. Please check the error message.","First_nameFail ");
		}
		
		} catch(Exception e) {
			failCase("Surname was not lacatable. Please check the error message.","SurnameFail ");
		}		
		} catch(Exception e) {
		failCase("Date of Birth was not locatable. Please check the error message.","BirthDateFail ");
	}
	}
	catch(Exception e) {
		failCase("Gender was not locatable. Please check the error message.","Gender TypeFail ");
	}
	}catch(Exception e) {
		failCase("Designation was not locatable. Please check the error message.","DesignationFail ");
	}
	}catch(Exception e) {
		failCase("NRC was not locatable. Please check the error message.","NextButtonFail ");
	}
    }catch(Exception e) {
	failCase("Click Button was not locatable. Please check the error message.","NextButtonFail ");
}
	}
}



