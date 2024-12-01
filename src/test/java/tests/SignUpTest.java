package tests;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import basedriver.BaseDriver;
import basedriver.PageDriver;
import pages.SignUpPage ;
import utilities.ExtentFactory;

public class SignUpTest extends BaseDriver{
	ExtentReports report;
	ExtentTest parentTest;
	ExtentTest childTest;
	
	@BeforeClass
	public void start() throws InterruptedException {
		PageDriver.getCurrentDriver().get(url);
		Thread.sleep(3000);
		report = ExtentFactory.getInstance();
		parentTest = report.createTest("<p style=\"color:#FF6000; font-size:20px\"><b>ORANGE HRM LOGIN</b></p>").assignAuthor("QA TEAM").assignDevice("Windows");
		
		
	}
	@Test
	public void SignUpTest() throws InterruptedException, IOException {
		childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:20px\"><b>LOGIN</b></p>");
		SignUpPage SignPage = new SignUpPage(childTest );
		SignPage.SignUp();
	}
	
	@AfterClass
	public void report() {
		report.flush();
	}

}

