package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {

	public WebDriver driver;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<>();
	
	
	
	public WebDriver init_driver(String browser) {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable notifications");
		DesiredCapabilities cp = new DesiredCapabilities();
		cp.setCapability(ChromeOptions.CAPABILITY, options);
		
		System.out.println("Browser name is"+browser);
		
		if(browser.equals("chrome")) {
			tlDriver.set(new ChromeDriver(options));
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
