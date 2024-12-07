package tests;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import basedriver.BaseDriver;
import basedriver.PageDriver;
import pages.Personal_InfoPage;
import pages.SignUpPage ;
import utilities.ExtentFactory;

public class Personal_Info_Test extends BaseDriver{
	ExtentReports report;
	ExtentTest parentTest;
	ExtentTest childTest;
	
	@BeforeClass
	public void start() throws InterruptedException {
		//PageDriver.getCurrentDriver().get(url);
		//Thread.sleep(3000);
		report = ExtentFactory.getInstance();
		parentTest = report.createTest("<p style=\"color:#FF6000; font-size:20px\"><b>Personal Info Done</b></p>").assignAuthor("QA TEAM").assignDevice("Windows");
		
		
	}
	@Test
	public void Personal_Info_Test() throws InterruptedException, IOException {
		childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:20px\"><b>Personal Info</b></p>");
		Personal_InfoPage PersonalPage = new Personal_InfoPage(childTest );
		PersonalPage.Personal_info();
	}
	
	@AfterClass
	public void report() {
		report.flush();
	}

}

