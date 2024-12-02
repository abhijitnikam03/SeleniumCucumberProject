package com.page;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PracticePage {

	WebDriver driver;
	WebDriverWait wait;
//##################constructor call###############################	

	public PracticePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
	}

//####################Page-Factory################################
	@FindBy(id = "autocomplete")
	WebElement countryname;

	@FindBy(xpath = "//div[starts-with(@id,'ui-id')]")
	List<WebElement> countryddl;

	@FindBy(id = "dropdown-class-example")
	WebElement dropdown;

	@FindBy(id = "openwindow")
	WebElement openwindow;

	@FindBy(xpath = "//a[@href='https://www.udemy.com/user/testing-minds' and @class='main-btn' and contains(test(),'Access')]")
	WebElement allcourse;

	@FindBy(id = "opentab")
	WebElement opentab;

	@FindBy(id = "alertbtn")
	WebElement alerthandle;

	@FindBy(xpath = "//legend[text()='Web Table Example']")
	WebElement webtable;

	@FindBy(id = "show-textbox")
	WebElement showbutton;

	@FindBy(id = "displayed-text")
	WebElement hidetxtbx;

	@FindBy(id = "mousehover")
	WebElement mousehover;

	@FindBy(id = "courses-iframe")
	WebElement switchframe;

	@FindBy(xpath = "//a[@href='lifetime-access']")
	WebElement links;
	// #######################web elements actions##########################

	public void radiobutton(String button) {
		WebElement rbutton = driver.findElement(By.xpath("//input[@value='" + button + "']"));
		wait.until(ExpectedConditions.visibilityOf(rbutton));
		rbutton.click();
	}

	public void country_name(String cname) throws InterruptedException {
		countryname.sendKeys(cname);
		Thread.sleep(3000);
		int cnt = countryddl.size();
		for (int i = 0; i < cnt; i++) {
			String cntname = countryddl.get(i).getText();
			if (cntname.toLowerCase().equals(cname)) {
				System.out.println("country name is" + cname);
				countryddl.get(i).click();
				break;
			}
		}
	}

	public void dropdown(String ddl) {
		wait.until(ExpectedConditions.visibilityOf(dropdown));
		Select sel = new Select(dropdown);
		sel.selectByValue(ddl);
	}

	public void checkbox(String cbox) {
		WebElement chbox = driver.findElement(By.xpath("//input[@type='checkbox' and @value='" + cbox + "']"));
		wait.until(ExpectedConditions.visibilityOf(chbox));
		chbox.click();
	}

	public void openwindow() {
		wait.until(ExpectedConditions.visibilityOf(dropdown));
		openwindow.click();

		Set<String> newwindow = driver.getWindowHandles();

		for (String windowhandle : newwindow) {
			driver.switchTo().window(windowhandle);
			String title = driver.getTitle();
			System.out.println("Page title is" + title);
			if (title.contains("QAClick Academy")) {
				wait.until(ExpectedConditions.visibilityOf(allcourse));
				allcourse.isEnabled();
				break;

			}
		}
	}

	public void opentab() {
		wait.until(ExpectedConditions.visibilityOf(opentab));
		opentab.click();
		Set<String> newwindow = driver.getWindowHandles();

		for (String windowhandle : newwindow) {
			driver.switchTo().window(windowhandle);
			String title = driver.getTitle();
			System.out.println("Page title is" + title);
			if (title.contains("QAClick Academy")) {
				wait.until(ExpectedConditions.visibilityOf(allcourse));
				allcourse.isEnabled();
				break;

			}
		}
	}

	public void alerthandle() {
		wait.until(ExpectedConditions.visibilityOf(alerthandle));
		alerthandle.click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	public void tabledata(String course) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", webtable);
		wait.until(ExpectedConditions.visibilityOf(webtable));
		String courseprc = driver
				.findElement(
						By.xpath("//table//tbody//tr//td[contains(text(),'" + course + "')]//following-sibling::td"))
				.getText();
		System.out.println("course price" + courseprc);

	}

	public void showbtn() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", showbutton);
		wait.until(ExpectedConditions.visibilityOf(showbutton));
		showbutton.click();

	}

	public void entertxt(String txt) {
		wait.until(ExpectedConditions.visibilityOf(hidetxtbx));
		hidetxtbx.sendKeys(txt);

	}

	public void mouseover() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", mousehover);
		wait.until(ExpectedConditions.visibilityOf(mousehover));
		Actions act = new Actions(driver);
		act.moveToElement(mousehover).build().perform();
	}

	public void iframe() throws InterruptedException {
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(switchframe);

	}
}
