package pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class LoginPageObject {
	// browser filed variable
	WebDriver browser;

	/**
	 * to set the browser
	 * 
	 * @param type
	 */
	public void setBrowser(int type) {
		if (type == 1) {
			browser = new FirefoxDriver();
		} else if (type == 2) {
			String path = ClassLoader.getSystemResource("chromedriver.exe").getPath();
			System.setProperty("webdriver.chrome.driver", path);
			browser = new ChromeDriver();
		} else if (type == 3) {
			String path = ClassLoader.getSystemResource("IEDriverServer.exe").getPath();
			System.setProperty("webdriver.ie.driver", path);
			browser = new InternetExplorerDriver();
		}

		// open the url
		browser.get("http://books.rediff.com");

		// implicit wait
		browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//maxmize the window
		browser.manage().window().maximize();
	}

	/**
	 * click on the sign in button in the login page
	 */
	public void clickSigninButton() {
		System.out.println("click button");
		browser.findElement(By.cssSelector("#sigin_info > a:nth-child(1)"))
				.click();
	}

	/**
	 * enter the username in login page
	 * 
	 * @param username
	 */
	public void enterUserName(String username) {
		browser.findElement(By.name("logid")).sendKeys(username);
	}

	/**
	 * enter the password in the login page
	 * 
	 * @param pwd
	 */
	public void enterPassword(String pwd) {
		browser.findElement(By.name("pswd")).sendKeys(pwd);
	}

	/**
	 * do login
	 */
	public void login() {
		browser.findElement(
				By.cssSelector("body > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > table:nth-child(1) > tbody:nth-child(5) > tr:nth-child(6) > td:nth-child(2) > input:nth-child(1)"))
				.click();
	}

	/**
	 * close the browser
	 */
	public void closeBrowser() {
		browser.quit();
	}
}
