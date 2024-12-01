package pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import basedriver.PageDriver;
import utilities.GetScreenShot;

public class SignUpPage {
	
	ExtentTest test;
	
	public SignUpPage(ExtentTest test) {
		PageFactory.initElements(PageDriver.getCurrentDriver(),this);
		this.test = test;
	}
	
	@FindBy(xpath="//a[contains(text(),'Sign up')]")
	WebElement SignUpButton;
	
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
	
	public void SignUp() throws IOException {
		try { 
			test.info("Click on the SignUp Button");
			if(SignUpButton.isDisplayed()) {
				SignUpButton.click();
				Thread.sleep(2000);
				passCaseWithSc("SignUpButton Successful","SignUpPass.");
			}
       }catch(Exception e) {
			failCase("SignUp Button was not locatable. Please check the error message.","GignUpFail ");
		}
	}

}
