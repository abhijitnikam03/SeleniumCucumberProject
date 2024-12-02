package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	public WebDriver driver;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<>();
	
	public WebDriver init_driver(String browser) {
		
		System.out.println("Browser name is"+browser);
		
		if(browser.equals("chrome")) {
			tlDriver.set(new ChromeDriver());
		}else if(browser.equals("firefox")) {
			tlDriver.set(new FirefoxDriver());
		}else if(browser.equals("edge")) {
			tlDriver.set(new EdgeDriver());
		}else {
			System.out.println("Please pass correct browser");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
}
