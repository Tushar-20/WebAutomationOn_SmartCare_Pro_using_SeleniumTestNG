package basedriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseDriver {
	protected static String url ="https://staging-scweb.arcapps.org/";
	WebDriver driver;
	
	
	@BeforeSuite
	public void startBrowser() {
		String browserName = System.getProperty("browser","chrome");
		 if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
			else if(browserName.equals("Microsoft Edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				
			}else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		 PageDriver.getInstance().setDriver(driver);
	}
	@AfterSuite
	public void close() {
		driver.close();
	}

}
