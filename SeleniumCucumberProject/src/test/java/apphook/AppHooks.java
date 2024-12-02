package apphook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import util.ConfigReader;
import util.ScreenRecorderUtil;

public class AppHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	String screenshotname;
	Path screenshotpath;
	Properties prop;
	ScreenRecorderUtil su;

	@Before(order = 0)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		System.out.println("Browser name is" + prop);

	}

	@Before(order = 1)
	public void launchbrowser() {
		String browserName = prop.getProperty("browser");
		System.out.println("Browser name is" + browserName);
		File file = new File("recordings/");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);

	}

	@Before(order = 2)
	public void filespath(Scenario scenario) throws Exception {
		ScreenRecorderUtil.startRecord("filespath");

		String folderPath = "./reports";
		File folder = new File(folderPath);
		if (deleteDirectory(folder)) {
			System.out.println("Folder deleted successfully.");
		} else {
			System.out.println("Failed to delete the folder.");
		}

	}

	@After(order = 0)
	public void quitbrowser(Scenario sce) throws Exception {
		ScreenRecorderUtil.stopRecord();
		screenshotname = sce.getName().replaceAll(" ", "_");
		Shutterbug.shootPage(driver, Capture.FULL, false).save("reports/test-Report/ScreenShot/");
		driver.quit();
	}

	private static boolean deleteDirectory(File directory) {
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files != null) { // Ensure it is not null
				for (File file : files) {
					deleteDirectory(file); // Recursive call for each file/directory
				}
			} else {
				System.out.println("File not deleted");
			}
		}
		return directory.delete(); // Delete the directory or file
	}
}
