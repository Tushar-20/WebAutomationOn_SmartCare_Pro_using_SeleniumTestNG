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
	
	ExcelUtils excelData = new ExcelUtils();
	
	public LoginPage(ExtentTest test) {
		PageFactory.initElements(PageDriver.getCurrentDriver(),this);
		this.test = test;
	}
	
	@FindBys ({
		@FindBy(xpath="//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/div[2]/input[1]"),
	     @FindBy(xpath=("//input[@name=\"username\"]"))
	})
	WebElement username;
	
	@FindBy(xpath= ("//input[@name=\"password\"]"))
	WebElement password;
	
	@FindBy(xpath="//Button[@type=\"submit\"]")
	WebElement loginButton;
	
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
		excelData.ReadExcel();
		try {
			test.info("Please enter username");
			if(username.isDisplayed()) {
				username.sendKeys(excelData.username);
				passCase("Username entered");
			}
			try {
				test.info("Please enter Password");
				if(password.isDisplayed()) {
					password.sendKeys(excelData.password);
					passCase("password send");
				}
				try {
					test.info("Click on the login");
					if(loginButton.isDisplayed()) {
						loginButton.click();
						Thread.sleep(2000);
						passCaseWithSc("Login Successful","loginPass.");
					}
		} catch(Exception e) {
			failCase("User name was not lacatable. Please check the error message.","usernameFail ");
		}
		
		
		} catch(Exception e) {
			failCase("Password was not lacatable. Please check the error message.","PasswordFail ");
		}
		
		
		
		} catch(Exception e) {
			failCase("Login Button was not locatable. Please check the error message.","PasswordFail ");
		}
	}

}
