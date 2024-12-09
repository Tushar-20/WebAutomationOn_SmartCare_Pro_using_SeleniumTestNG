package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import basedriver.PageDriver;
import utilities.ExcelUtils;
import utilities.GetScreenShot;

public class LoginPage {
	ExtentTest test;
	
	
	public LoginPage(ExtentTest test) {
		PageFactory.initElements(PageDriver.getCurrentDriver(),this);
		this.test = test;
	}
	
    @FindBy(xpath="//input[@placeholder='Add Username']")
    WebElement username;
	
	@FindBy(xpath= ("//input[@placeholder='Type Password']"))
	WebElement password;
	
	@FindBy(xpath= ("//input[@placeholder='Type Confirm Password']"))
	WebElement ConfirmPassword;
	
	@FindBy(xpath="//button[contains(text(),'Submit')]")
	WebElement SubmitButton;
	
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
	
	public void login() throws InterruptedException, IOException {
		try {
			test.info("Please enter username");
			if(username.isDisplayed()) {
				username.sendKeys("Tushar Chandra Sarker");
				passCase("Username entered");
				Thread.sleep(2000);
			}
			try {
				test.info("Please enter Password");
				if(password.isDisplayed()) {
					password.sendKeys("SarkerTushar2020@");
					passCase("password send");
					Thread.sleep(4000);
				}
				try {
					test.info("Please enter Password Again");
					if(ConfirmPassword.isDisplayed()) {
						ConfirmPassword.sendKeys("SarkerTushar2020@");
						passCase("password send again");
					}
				try {
					test.info("Click on the login");
					if(SubmitButton.isDisplayed()) {
						SubmitButton.click();
						Thread.sleep(2000);
						passCaseWithSc("SubmitButton create Successful","loginPass.");
					}
		} catch(Exception e) {
			failCase("User name was not lacatable. Please check the error message.","usernameFail ");
		}	
		} catch(Exception e) {
			failCase("Password was not lacatable. Please check the error message.","PasswordFail ");
		}
		}catch(Exception e) {
			failCase("Password didnot match. Please check the error message.","PasswordConfirmationFail ");
		}
		} catch(Exception e) {
			failCase("SubmitButton was not locatable. Please check the error message.","LoginFail ");
		}
	}
}
