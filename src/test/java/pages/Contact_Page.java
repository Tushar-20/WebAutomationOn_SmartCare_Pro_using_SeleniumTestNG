package pages;

import java.io.IOException;
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

public class Contact_Page {
	ExtentTest test;
	
	public Contact_Page(ExtentTest test) {
		PageFactory.initElements(PageDriver.getCurrentDriver(),this);
		this.test = test;
	}
	
	@FindBy(xpath= ("//textarea[@placeholder='Add Address']"))
	WebElement Address;
	@FindBy(xpath=("//select[@placeholder='Add Code']"))
	WebElement Code;
	@FindBy(xpath=("//input[@placeholder='Cellphone']"))
	WebElement Cellphone;
	@FindBy(xpath="//button[normalize-space()='Next']")
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
	
	public void Contact_Info() throws InterruptedException, IOException {
		try {
			test.info("Please enter your Address");
			if(Address.isDisplayed()) {
				Address.sendKeys("Narsingdi,Dhaka,Bangladesh");
				Thread.sleep(3000);
				passCase("Address entered");
			}try {
					test.info("Please enter Code");
					if(Code.isDisplayed()) {
						Code.click();
						 Thread.sleep(5000);
						 
						Select selectcode = new Select(Code);						
						selectcode.selectByVisibleText("BD (+880)");;
			            Thread.sleep(5000);
					}
				try {
					test.info("Please enter Cellphone");
					if(Cellphone.isDisplayed()) {
						Cellphone.sendKeys("1741540312");
						passCase("Cellphone entered");
						Thread.sleep(5000);
					}try {
						test.info("Click on the Next");
						if(NextButton.isDisplayed()) {
							NextButton.click();
							Thread.sleep(5000);
							passCaseWithSc("Next Button click Successful","NextPass.");
						}
					
					}catch(Exception e) {
			failCase("Contact Address was not lacatable. Please check the error message.","Contact AddressFail ");
		}
		
		} catch(Exception e) {
			failCase("Code was not lacatable. Please check the error message.","CodeFail ");
		}		
		} catch(Exception e) {
		failCase("Cellphone was not locatable. Please check the error message.","CellphoneFail ");
	}
	}catch(Exception e) {
		failCase("NextButton was not locatable. Please check the error message.","NextButton ");
	}
	}
}



