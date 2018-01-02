package com.autogmail.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.autogmail.exceptions.InvalidConfigException;

public class Base {
	private WebDriver driver;

	public WebDriver setbrowser(String brwser) throws InvalidConfigException {
		brwser = (brwser == null ? "" : brwser);

		switch (brwser.toLowerCase()) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "C://Users/saritha.pattathil/geckodriver.exe");
			this.driver = new FirefoxDriver();
			break;

		case "chrome":
			System.setProperty("webdriver.chrome.driver", "C://Users/saritha.pattathil/chromedriver.exe");
			this.driver = new ChromeDriver();
			break;
		default:
			throw new InvalidConfigException("Invalid browser: [" + brwser + "]");

		}
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return this.driver;
	}

	public WebDriver getDriver() throws InvalidConfigException {
		if (this.driver == null) {
			this.setbrowser("");
		}

		return this.driver;
	}

	public void entertxt(String txtboxid, String txtvalue) throws InvalidConfigException {
		this.getDriver().findElement(By.xpath(txtboxid)).sendKeys(txtvalue);
	}

	public void click(String elemid) throws InvalidConfigException {
		this.getDriver().findElement(By.xpath(elemid)).click();
	}

	public WebElement findEle(String xpath) throws InvalidConfigException {
		try {
			WebElement we = this.getDriver().findElement(By.xpath(xpath));

			return (we.isDisplayed() ? we : null);
		} catch (NoSuchElementException e) {
			// eat exception
		}

		return null;
	}

	public String getTxt(String xpathElmt) {
		try {
			WebElement we = this.findEle(xpathElmt);
			if (we != null) {
				return we.getText();
			}
		} catch (InvalidConfigException ie) {
			// eat it
		}
		return null;
	}
}
