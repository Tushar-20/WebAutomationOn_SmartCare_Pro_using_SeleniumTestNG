package tests;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import basedriver.BaseDriver;
import basedriver.PageDriver;
import pages.Contact_Page;
import pages.Personal_InfoPage;
import pages.SignUpPage ;
import utilities.ExtentFactory;

public class Contact_Test extends BaseDriver{
	ExtentReports report;
	ExtentTest parentTest;
	ExtentTest childTest;
	
	@BeforeClass
	public void start() throws InterruptedException {
		//PageDriver.getCurrentDriver().get(url);
		//Thread.sleep(3000);
		report = ExtentFactory.getInstance();
		parentTest = report.createTest("<p style=\"color:#FF6000; font-size:20px\"><b>Contact_Info Done</b></p>").assignAuthor("QA TEAM").assignDevice("Windows");
		
		
	}
	@Test
	public void Contact_Test() throws InterruptedException, IOException {
		childTest = parentTest.createNode("<p style=\"color:#3E96E7; font-size:20px\"><b>Contact_Info</b></p>");
		Contact_Page Contact = new Contact_Page(childTest );
		Contact.Contact_Info();
	}
	
	@AfterClass
	public void report() {
		report.flush();
	}

}


